package com.craft.rms.Base;

import com.craft.rms.utils.BeanUtils;
import com.craft.rms.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一规格给客户端响应数据.
 * <p>
 * 修改时间：2016/3/21
 * </p>
 * 
 * @author 颜鹏飞（在原基础上修改） 增加异常状态为ResultStatus.EX 增加客户端分页数据的响应方法listResult(Pager,);
 *         <p>
 *         修改时间：2016/3/24
 *         </p>
 * @author 颜鹏飞（修订）
 * 
 */
public final class AjaxReturnInfo implements ResultStatus{
    
    /** 日志处理对象. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AjaxReturnInfo.class);
    /** 父级业务编号，值为-1 */
    private static final String PARENT_FLAG = "-1";
    /** 当前操作的状态. */
    private String success; // true or false
    /** 总记录数，它的值不能<0. */
    private int total;
    /** 当前页的数据. */
    private List<?> rows = new ArrayList(1);

    private String message; // 返回信息
    private Exception exception;
    private Map<String, Object> datas = new HashMap<String, Object>(); // 返回的数据

    private AjaxReturnInfo() {
    }

    /**
     * 私有的构造函数
     * 
     * @param result
     *            'true' or 'false'
     */
    private AjaxReturnInfo(String result) {
        this.success = result;
    }

    /**
     * @return 总记录数
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total
     *            总记录数，它的值不能为负数
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return 当前页数据，它是一个java.util.List集合
     */
    @SuppressWarnings("rawtypes")
    public List< ? > getRows() {
        return rows;
    }

    /**
     * 设置当前页数据.
     * 
     * @param rows
     *            当前页数据
     */
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * 打印日志信息：操作的结果.
     */
    private void printOpratedLog() {
        LOGGER.debug("=====： {}", this.message);
    }
    /**
     * 构建树信息List<Map<String, Object>>
     * @param treeData List集合中存储实体信息，用来提取树结构信息的
     * @param idFieldName id字段名，对应实体的属性名，通常对应着数据库中的主键
     * @param textFieldName text字段名，对应实体的属性名，节点所显示的内容
     * @param iconCls 节点的图标
     * @param type 节点是哪一级别的，如数据库类别、Schema类型、表类型等
     * @return
     */
    public static List<Map<String, Object>> buildTree(final List<?> treeData,
            final String idFieldName, final String textFieldName, final String iconCls, final String type) {
        List<Map<String, Object>> tree = new ArrayList<Map<String,Object>>();
        if(treeData != null){
            int size = treeData.size();
            if (size > 0) {
                try {
                    Class<?> c = treeData.get(0).getClass();
                    Field idField = c.getDeclaredField(idFieldName);
                    Field textField = c.getDeclaredField(textFieldName);
                    idField.setAccessible(true);
                    textField.setAccessible(true);
                    for(int i=0; i < size; i++){
                        Object entity = treeData.get(i);
                        String id = idField.get(entity).toString();//如果id值为空则抛出异常
                        String text = (String) textField.get(entity);
                        Map<String, Object> attributes = new HashMap<String, Object>(1);
                        attributes.put("type", type);// 设置附加属性
                        Map<String, Object> node = buildTreeNode(id, text, iconCls, attributes);
                        LOGGER.debug("树节点：{}",node);
                        tree.add(node);
                    }
                    idField.setAccessible(false);
                    textField.setAccessible(false);
                } catch (Exception e) {
                    LOGGER.error("构建Tree出现异常", e);
                }
            }
        }
        return tree;
    }
    
//    public static List<Map<String, Object>> buildTree(List<?> treeData,
//            String idFieldName, String textFieldName, String iconCls, String type, String attributesJson) {
//        List<Map<String, Object>> tree = new ArrayList<Map<String,Object>>();
//        if (treeData != null) {
//            int size = treeData.size();
//            if (size > 0) {
//                try {
//                    Class<?> c = treeData.get(0).getClass();
//                    Field idField = c.getDeclaredField(idFieldName);
//                    Field textField = c.getDeclaredField(textFieldName);
//                    idField.setAccessible(true);
//                    textField.setAccessible(true);
//                    for (int i = 0; i < size; i++) {
//                        Object entity = treeData.get(i);
//                        String id = idField.get(entity).toString();// 如果id值为空则抛出异常
//                        String text = (String) textField.get(entity);
//                        Map<String, Object> attributes = buildTreeNodeAttribute(entity,type,attributesJson);
//                        Map<String, Object> node = buildTreeNode(id, text, iconCls, attributes);
//                        LOGGER.debug("树节点：{}", node);
//                        tree.add(node);
//                    }
//                    idField.setAccessible(false);
//                    textField.setAccessible(false);
//                } catch (Exception e) {
//                    LOGGER.error("构建Tree出现异常", e);
//                }
//            }
//        }
//        return tree;
//    }
//    
//    /**
//     * 构建树节点属性
//     * @param type
//     * @param attributesJson
//     * @return
//     */
//    private static Map<String, Object> buildTreeNodeAttribute(Object entity, String type, String attributesJson) {
//         Map<String, Object> attributes = JSON.parseObject(attributesJson, HashMap.class);
//         for (String key : attributes.keySet()) {
//            String fieldName = (String) attributes.get(key);
//            //TODO 待完成
//        }
//         attributes.put("type", type);
//        return attributes;
//    }

    /**
     * 构建树的单个节点
     * @param idFieldValue
     * @param textFieldValue
     * @param iconCls
     * @param attributes
     * @return
     */
    public static Map<String, Object> buildTreeNode(String idFieldValue, String textFieldValue, String iconCls,Map<String, Object> attributes){
        Map<String, Object> treeNode = new HashMap<String, Object>();
        treeNode.put("id", idFieldValue);
        treeNode.put("text", textFieldValue);
        treeNode.put("iconCls", iconCls);// 设置图标属性
        treeNode.put("attributes", attributes);
        return treeNode;
    }
    /**
     * 给EasyUI的treegrid设置数据
     * @param data 待转换的结果集
     * @param parentIdName 存储父编号的属性名
     * @return
     * @author ypf 2016/04/12
     */
    public static AjaxReturnInfo toEasyUITreegrid(List<?> data, String parentIdName){
        AjaxReturnInfo result = new AjaxReturnInfo();
        List<Map<String, String>> treeList = new ArrayList<Map<String,String>>();
        if(data != null){
            try {
                for (Object entity : data) {
                    Map<String, String> row = BeanUtils.describe(entity);
                    String parentId = row.get(parentIdName);
                    if(!PARENT_FLAG.equals(parentId)){
                        row.put("_parentId", parentId);
                    }
                    treeList.add(row);
                    LOGGER.debug("treegrid行的信息：{}",row);
                }
            } catch (Exception e) {
//                e.printStackTrace();
                LOGGER.error("加载treegrid出现异常：",e);
            }
            result.total = data.size();
        }
        result.rows = treeList;
        return result;
    }
//    /**
//     * 得到下拉列表的所有信息
//     * @param data 下拉列表中所有的id和text值
//     * @param valueFieldName id的字段名称
//     * @param textFieldName text的字段名称
//     * @return
//     */
//    public static List<Combobox> toEasyUICombobox(List<?> data, String valueFieldName,String textFieldName){
//        return toEasyUICombobox(data, valueFieldName, textFieldName,null);
//    }
//    /**
//     * 得到下拉列表的所有信息
//     * @param data 下拉列表中所有的id和text值
//     * @param valueFieldName id的字段名称
//     * @param textFieldName text的字段名称
//     * @param idFieldValueSelected 选中那项目的id值
//     * @return
//     */
//    public static List<Combobox> toEasyUICombobox(List<?> data, String valueFieldName,String textFieldName,String idFieldValueSelected){
//        List<Combobox> dataCbox = new ArrayList<Combobox>();
//        Combobox cboxDefault = new Combobox("","请选择....",true); 
//        dataCbox.add(cboxDefault);
//        if(data != null){
//            int size=data.size();
//            if(size>0){
//                Object obj = data.get(0);
//                Class c = obj.getClass();
//                try {
//                    Field valueField = c.getDeclaredField(valueFieldName);
//                    Field textField = c.getDeclaredField(textFieldName);
//                    valueField.setAccessible(true);
//                    textField.setAccessible(true);
//                    for(int i = 0; i < size; i++){
//                        Object o = data.get(i);
//                        Object idV = valueField.get(o);
//                        Object textV = textField.get(o);
//                        String idValue = idV.toString();//id值为空或text值为空都将出现异常
//                        String textValue = textV.toString();
//                        Combobox cbox = new Combobox(idValue, textValue);
//                        if(StringUtils.hasText(idFieldValueSelected) && idValue.equals(idFieldValueSelected)){
//                            cbox.setSelected(true);
//                        }
//                        dataCbox.add(cbox);
//                    }
//                    valueField.setAccessible(false);
//                    textField.setAccessible(false);
//                } catch (Exception e) {
//                    LOGGER.error("加载下拉列表的数据出现异常：",e);
//                    Combobox cb = new Combobox("-1", "数据加载错误...", true);
//                    dataCbox.add(cb);
//                }
//            }
//        }
//        LOGGER.debug("下拉框数据个数：{},数据：{}",dataCbox.size(),dataCbox);
//        return dataCbox;
//    }
    /**
     * 得到下拉列表的所有信息，不增加第一项item
     * <p>
     * @see {@link #toCombobox(List, String, String, String, String)}<br/>
     * {@link #toComboboxAddFirstItem(List, String, String, String)}<br/>
     * {@link #toComboboxAddFirstItemDefault(List, String, String)}<br/>
     * </p>
     * @param data 下拉列表中所有的id和text值
     * @param valueFieldName 客户端Combobox的id字段名称
     * @param textFieldName 客户端Combobox的text字段名称
     * @return
     */
    public static List<Map<String, Object>> toCombobox(final List<?> data, final String valueFieldName, final String textFieldName){
        return toCombobox(data, valueFieldName, textFieldName, null, null);
    }
    /**
     * 得到下拉列表的所有信息（传参设置默认选中项）
     * @param data 下拉列表中所有的id和text值
     * @param valueFieldName 客户端Combobox的id字段名称
     * @param textFieldName 客户端Combobox的text字段名称
     * @param selectedItemIdValue 选中项的id值
     * @return
     */
    public static List<Map<String, Object>> toComboboxWithSelected(final List<?> data, final String valueFieldName, final String textFieldName, final String selectedItemIdValue){
        return toCombobox(data, valueFieldName, textFieldName, selectedItemIdValue, null);
    }
    /**
     * 得到下拉列表的所有信息，增加第一项默认的选中项"请选择...."【注意：默认选中第一项】
     * <p>
     * @see {@link #toCombobox(List, String, String)}<br/>
     * {@link #toCombobox(List, String, String, String, String)}<br/>
     * {@link #toComboboxAddFirstItem(List, String, String, String)}<br/>
     * </p>
     * @param data 下拉列表中所有的id和text值，不包含要增加的第一项"请选择...."
     * @param valueFieldName 客户端Combobox的id字段名称
     * @param textFieldName 客户端Combobox的text字段名称
     * @return
     * @author ypf 2014/04/21
     */
    public static List<Map<String, Object>> toComboboxAddFirstItemDefault(final List<?> data, final String valueFieldName, final String textFieldName){
        return toCombobox(data, valueFieldName, textFieldName, null, "请选择....");
    }
    /**
     * 得到下拉列表的所有信息，增加第一项item指定的默认选中项
     * 例：toComboboxAddFirstItem（data,"id","text","请选择...");
     * <p>
     * @see {@link #toCombobox(List, String, String)}<br/>
     * {@link #toCombobox(List, String, String, String, String)}<br/>
     * {@link #toComboboxAddFirstItemDefault(List, String, String)}<br/>
     * </p>
     * @param data 下拉列表中所有的id和text值，不包含要增加的第一项item信息
     * @param valueFieldName 客户端Combobox的id字段名称
     * @param textFieldName 客户端Combobox的text字段名称
     * @param firstItemName 增加的第一项item的信息，如："请选择...." 、 "全部" 等。【默认选中第一项】
     * @return
     * @author ypf 2014/04/21
     */
    public static List<Map<String, Object>> toComboboxAddFirstItem(final List<?> data, final String valueFieldName, final String textFieldName, final String firstItemName){
        return toCombobox(data, valueFieldName, textFieldName, null, firstItemName);
    }
    /**
     * 构建下拉列表第一项的信息（通常是提示的信息，例如："请选择...." 、 "所有的" 等）
     * @param valueFieldName 客户端Combobox的id字段名称
     * @param textFieldName 客户端Combobox的text字段名称
     * @param defaultValue
     * @return
     */
    private static Map<String, Object> buildComboboxFirstItem(final String valueFieldName, final String textFieldName, final String defaultValue){
         Map<String, Object> cboxDefault = new HashMap<String, Object>(3);
         cboxDefault.put(valueFieldName, "");
         cboxDefault.put(textFieldName, defaultValue);
         cboxDefault.put("selected", true);
         return cboxDefault;
    }
    /**
     * 得到下拉列表的所有信息，增加第一项item信息。
     * idFieldValueSelected和firstItemName都设置了默认选中，它们都有值时会默认选中idFieldValueSelected
     * <p>
     * @see {@link #toCombobox(List, String, String)}<br/>
     * {@link #toComboboxAddFirstItem(List, String, String, String)}<br/>
     * {@link #toComboboxAddFirstItemDefault(List, String, String)}<br/>
     * </p>
     * @param data 下拉列表中所有的id和text值
     * @param valueFieldName 客户端Combobox的id字段名称
     * @param textFieldName 客户端Combobox的text字段名称
     * @param idFieldValueSelected 选中那项的id值
     * @param firstItemName 增加的第一项item的信息，如："请选择...." 、 "全部" 等。如果此项为空，则在下拉列表中不增加这项item
     * @return 
     */
    public static List<Map<String, Object>> toCombobox(final List<?> data, final String valueFieldName, final String textFieldName, final String idFieldValueSelected, final String firstItemName){
        List<Map<String, Object>> dataCbox = new ArrayList<Map<String, Object>>();
        if(StringUtils.hasText(firstItemName)){
            //下拉列表中增加第一项item
            dataCbox.add(buildComboboxFirstItem(valueFieldName, textFieldName, firstItemName));
        }
        if(data != null){
            int size=data.size();
            if(size>0){
                Class c = data.get(0).getClass();
                try {
                    Field valueField = c.getDeclaredField(valueFieldName);
                    Field textField = c.getDeclaredField(textFieldName);
                    valueField.setAccessible(true);
                    textField.setAccessible(true);
                    for(int i = 0; i < size; i++){
                        Object o = data.get(i);
                        Object idV = valueField.get(o);
                        Object textV = textField.get(o);
                        String idValue = idV.toString();//id值为空或text值为空都将出现异常
                        String textValue = textV.toString();
                        Map<String, Object> cbox = new HashMap<String, Object>(3);
                        cbox.put(valueFieldName, idValue);
                        cbox.put(textFieldName, textValue);
                        if(StringUtils.hasText(idFieldValueSelected) && idValue.equals(idFieldValueSelected)){
                            cbox.put("selected", true);
                        }
                        dataCbox.add(cbox);
                    }
                    valueField.setAccessible(false);
                    textField.setAccessible(false);
                } catch (Exception e) {
                    LOGGER.error("加载下拉列表的数据出现异常：",e);
                    Map<String, Object> cb = new HashMap<String, Object>(3);
                    cb.put(valueFieldName, "-1");
                    cb.put(textFieldName, "数据加载错误...");
                    cb.put("selected", true);
                    dataCbox.add(cb);
                }
            }
        }
        LOGGER.debug("下拉框数据个数：{},数据：{}",dataCbox.size(),dataCbox);
        return dataCbox;
    }

    /**
     * 得到下拉列表的所有信息
     * @param data 数据集
     * @param idFieldName 作为combotree的id
     * @param textFieldName 作为combotree的text
     * @param iconCls 图标
     * @param args 作为combotree的attributes
     * @return
     * 例：idFieldName="connectionId", textFieldName="connectionName", args[1]="connectionType"
     * [{id: connection.getConnectionId, text: connection.getConnectionName, attributes: [{connectionType: connection.getConnectionType}]}]
     */
    public static List<Map<String, Object>> toCombotree(List<?> data, String idFieldName, String textFieldName, String iconCls, String... args) {
        List<Map<String, Object>> dataCombotree = new ArrayList<Map<String, Object>>();
        if (data != null) {
            int size = data.size();
            if (size > 0) {
                Class c = data.get(0).getClass();
                try {
                    Field idField = c.getDeclaredField(idFieldName);
                    Field textField = c.getDeclaredField(textFieldName);

                    idField.setAccessible(true);
                    textField.setAccessible(true);

                    for (int i = 0; i < size; i++) {
                        Object o = data.get(i);
                        Object idV = idField.get(o);
                        Object textV = textField.get(o);
                        String idValue = idV.toString();
                        String textValue = textV.toString();
                        Map<String, Object> ctree = new HashMap<String, Object>(4);
                        ctree.put("id", idValue);
                        ctree.put("text", textValue);
                        ctree.put("iconCls", iconCls);
                        Map<String, Object> attributes = new HashMap<String, Object>();
                        for (String argsElement : args) {
                            Field elementField = c.getDeclaredField(argsElement);
                            elementField.setAccessible(true);
                            Object elementV = elementField.get(o);
                            String elementValue = elementV.toString();
                            attributes.put(argsElement, elementValue);
                            elementField.setAccessible(false);
                        }
                        ctree.put("attributes", attributes);
                        dataCombotree.add(ctree);
                    }

                    idField.setAccessible(false);
                    textField.setAccessible(false);
                } catch (Exception e) {
                    LOGGER.error("加载下拉列表数据出现异常：", e);
                    Map<String, Object> ctree = new HashMap<String, Object>(4);
                    ctree.put("id", "-1");
                    ctree.put("text", "数据加载错误...");
                    ctree.put("iconCls", null);
                    ctree.put("attributes", null);
                    dataCombotree.add(ctree);
                }
            }
        }
        LOGGER.debug("下拉框数据个数：{},数据：{}", dataCombotree.size(), dataCombotree);
        return dataCombotree;
    }


//    /*public static List<Map<String, Object>> getEasyUICombobox(List<?> data, String idFieldName,String textFieldName,String idFieldValueSelected){
//        List<Map<String, Object>> comboboxData = new ArrayList<Map<String,Object>>();
//        if(data != null && data.size()>0){
//            try {
//                Collection<Map<String, String>> dataMap = BeanUtils.toMapList(data);
//                Iterator<Map<String, String>> it = dataMap.iterator();
//                Map<String, Object> cbox1 = new HashMap<String, Object>(3);
//                cbox1.put("id", "");
//                cbox1.put("text", "请选择...");
//                cbox1.put("selected", true);
//                comboboxData.add(cbox1);
//                while(it.hasNext()){
//                    Map<String, Object> cbox = new HashMap<String, Object>(3);
//                    Map<String, String> b = it.next();
//                    String idValue = b.get(idFieldName);
//                    String textValue = b.get(textFieldName);
//                    if(!StringUtils.hasText(idValue) || !StringUtils.hasText(textValue)){
//                        textValue = "加载的数据有误";
//                    }
//                    cbox.put("id", idValue);
//                    cbox.put("text", textValue);
//                    if(idFieldValueSelected != null && idFieldValueSelected.equals(idValue)){
//                        cbox.put("selected", true);
//                    }
//                    comboboxData.add(cbox);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return comboboxData;
//    }*/
    
    /**
     * 设置分页数据.
     * 
     * @param pager
     *            分页模型,pager为null时代表系统出现异常
     * @param extraMsg
     *            提示信息
     * @return 给客户端响应的信息，一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo listResult(Pager pager, String extraMsg) {
        AjaxReturnInfo returnInfo = new AjaxReturnInfo();
        if (pager == null) {
            returnInfo.message = EX_MSG;// 系统异常信息
            returnInfo.success = FALSE;
        } else {
            returnInfo.success = TRUE;
            returnInfo.total = pager.getTotal();
            returnInfo.rows = pager.getData();
            if (extraMsg != null) {
                returnInfo.message = extraMsg;
            }
        }
        return returnInfo;
    }

    /**
     * 设置分页数据.
     * 
     * @param pager
     *            分页模型,pager为null时代表系统出现异常
     * @return
     */
    public static AjaxReturnInfo listResult(Pager pager) {
        return listResult(pager, null);
    }
    /**
     * 设置客户端EasyUI datagrid【不分页】显示所需要的数据
     * @param allData 所有的数据信息
     * @return
     */
    public static AjaxReturnInfo listResult(List<?> allData) {
        return listResult(allData, null);
    }
    /**
     * 设置客户端EasyUI datagrid【不分页】显示所需要的数据
     * @param allData 所有的数据信息
     * @param msg 额外信息
     * @return
     */
    public static AjaxReturnInfo listResult(List<?> allData, String msg) {
        AjaxReturnInfo returnInfo = new AjaxReturnInfo();
        if(allData != null){
            returnInfo.success = TRUE;
            returnInfo.total = allData.size();
            returnInfo.rows = allData;
            if(msg != null){
                returnInfo.message = msg;
            }
        }
        return returnInfo;
    }

    /**
     * <p>
     * 除了添加、修改、删除的操作，比如移动某个树叶子的操作.
     * </p>
     * 示例：opratedResult(result, "移动", extraMsg);
     * 
     * @param status
     * @param opMsg
     * @param extraMsg
     * @return
     */
    public static AjaxReturnInfo opratedResult(int status, String opMsg,
            String extraMsg) {
        AjaxReturnInfo result = new AjaxReturnInfo();
        String statusFlag = FALSE;
        if(status >= ResultStatus.SUCCESS){//status状态>=1时表示成功！其它状态皆为失败。
            statusFlag = TRUE;
            opMsg += "成功";
        }else if(status == ResultStatus.EX){
            opMsg += "失败";
            if (extraMsg != null) {
                extraMsg = EX_MSG + extraMsg;
            } else {
                extraMsg = EX_MSG;
            }
        }else{
            opMsg += "失败";
        }
        return result.setOpratedStatusMsg(statusFlag, opMsg, extraMsg);
    }
   
    /**
     * @param status 0FAIL操作失败；-1EX操作异常；-99OTHER其它操作情况（非成功和失败的状态），如不能授权。例opratedResult(OTHER, "提示信息")
     * @param opMsg
     * @return
     */
    public static AjaxReturnInfo opratedResult(int status, String opMsg) {
        return opratedResult(status, opMsg, null);
    }

    /**
     * 一般用于校验的提示信息
     *
     * @param status
     * @param validateMsg
     * @return
     */
    public static AjaxReturnInfo validateResult(int status, String validateMsg) {
        AjaxReturnInfo result = new AjaxReturnInfo();
        String opMsg = StringUtils.EMPTY;
        String expMsg = null;
        String statusFlag = TRUE;
        switch (status) {
            case ResultStatus.EX:
                expMsg = EX_MSG;
            case ResultStatus.FAIL:
                statusFlag = FALSE;
                opMsg = opMsg + validateMsg;
                break;
            case ResultStatus.OTHER:
                break;
            default:
                opMsg = opMsg + validateMsg;
                break;
        }
        return result.setOpratedStatusMsg(statusFlag, opMsg, expMsg);
    }

    /**
     * 保存成功或失败操作后给客户端响应信息.
     * 
     * @param status
     *            保存成功与否的状态：0代表保存失败;-1代表系统运行出现异常
     * @return 客户端响应的信息，是一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo saveResult(int status) {
        return saveResult(status, null);
    }

    /**
     * 保存成功或失败时需要给客户端<code>提示额外的信息</code>时调用此方法.<br/>
     * 
     * @param status
     *            保存成功与否的状态：0代表保存失败;-1代表系统运行出现异常
     * @param extraMsg
     *            额外的消息
     * @return 给客户端响应的信息，一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo saveResult(int status, String extraMsg) {
        AjaxReturnInfo result = new AjaxReturnInfo();
        if (status >= ResultStatus.SUCCESS) {
            return result.saveSuccess(extraMsg);
        } else if (status == ResultStatus.EX) {
            if (StringUtils.hasText(extraMsg)) {
                extraMsg = EX_MSG + extraMsg;
            } else {
                extraMsg = EX_MSG;
            }
        }
        return result.saveFail(extraMsg);
    }

    /**
     * 修改成功或失败操作后给客户端响应信息.
     * 
     * @param status
     *            修改成功与否的状态：0代表修改失败;-1代表系统运行出现异常
     * @return 客户端响应的信息，是一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo updateResult(int status) {
        return updateResult(status, null);
    }

    /**
     * 修改成功或失败时需要给客户端<code>提示额外的信息</code>时调用此方法.<br/>
     * 
     * @param status
     *            修改成功与否的状态：0代表修改失败;-1代表系统运行出现异常
     * @param extraMsg
     *            额外的消息
     * @return 给客户端响应的信息，一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo updateResult(int status, String extraMsg) {
        AjaxReturnInfo result = new AjaxReturnInfo();
        if(status >= ResultStatus.SUCCESS){
            return result.updateSuccess(extraMsg);
        }else if(status == ResultStatus.EX){
            if (StringUtils.hasText(extraMsg)) {
                extraMsg = EX_MSG + extraMsg;
            } else {
                extraMsg = EX_MSG;
            }
        }
        return result.updateFail(extraMsg);
    }

    /**
     * 删除成功或失败操作后给客户端响应信息.
     * 
     * @param status
     *            删除成功与否的状态：0代表删除失败;-1代表系统运行出现异常
     * @return 客户端响应的信息，是一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo deleteResult(int status) {
        return deleteResult(status, null);
    }

    /**
     * 删除成功或失败时需要给客户端<code>提示额外的信息</code>时调用此方法.<br/>
     * 
     * @param status
     *            删除成功与否的状态：0或-1代表删除失败;-1代表系统运行出现异常
     * @param extraMsg
     *            额外的消息
     * @return 给客户端响应的信息，一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public static AjaxReturnInfo deleteResult(int status, String extraMsg) {
        AjaxReturnInfo result = new AjaxReturnInfo();
        if (status >= ResultStatus.SUCCESS) {
            return result.deleteSuccess(extraMsg);
        } else if (status == ResultStatus.EX) {
            if (StringUtils.hasText(extraMsg)) {
                extraMsg = EX_MSG + extraMsg;
            } else {
                extraMsg = EX_MSG;
            }
        }
        return result.deleteFail(extraMsg);
    }

    public AjaxReturnInfo setOpratedStatusMsg(String status, String msgStatus) {
        return setOpratedStatusMsg(status, msgStatus, null);
    }

    public AjaxReturnInfo setOpratedStatusMsg(String status, String opMsg,
            String extraMsg) {
        this.success = status;
        if (StringUtils.hasText(extraMsg)) {
            StringBuilder sb = new StringBuilder(extraMsg.length() + 9);
            sb.append(opMsg);
            sb.append(" | ");
            sb.append(extraMsg);
            this.message = sb.toString();
        } else {
            this.message = opMsg;
        }
        printOpratedLog();
        return this;
    }

    public AjaxReturnInfo saveSuccess() {
        return saveSuccess(null);
    }

    /**
     * 保存成功后给客户端返回<code>SUCCES_SAVE保存成功和额外</code>的消息.
     * 
     * @param extraMsg
     *            额外的信息
     * @return 一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public AjaxReturnInfo saveSuccess(String extraMsg) {
        return setOpratedStatusMsg(TRUE, SUCCES_SAVE, extraMsg);
    }

    public AjaxReturnInfo saveFail() {
        return setOpratedStatusMsg(FALSE, FAIL_SAVE);
    }

    /**
     * 保存失败后给客户端返回<code>FAIL_SAVE保存失败和额外</code>的消息.
     * 
     * @param extraMsg
     *            额外的信息
     * @return 一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public AjaxReturnInfo saveFail(String extraMsg) {
        return setOpratedStatusMsg(FALSE, FAIL_SAVE, extraMsg);
    }

    public AjaxReturnInfo updateSuccess() {
        return setOpratedStatusMsg(TRUE, SUCCES_UPDATE);
    }

    /**
     * 修改成功后给客户端返回<code>SUCCES_UPDATE修改成功和额外</code>的消息.
     * 
     * @param extraMsg
     *            额外的信息
     */
    public AjaxReturnInfo updateSuccess(String extraMsg) {
        return setOpratedStatusMsg(TRUE, SUCCES_UPDATE, extraMsg);
    }

    public AjaxReturnInfo updateFail() {
        return setOpratedStatusMsg(FALSE, FAIL_UPDATE);
    }

    /**
     * 修改失败后给客户端返回<code>FAIL_UPDATE修改失败和额外</code>的消息.
     * 
     * @param extraMsg
     *            额外的信息
     */
    public AjaxReturnInfo updateFail(String extraMsg) {
        return setOpratedStatusMsg(FALSE, FAIL_UPDATE, extraMsg);
    }

    public AjaxReturnInfo deleteSuccess() {
        return setOpratedStatusMsg(TRUE, SUCCES_DEL);
    }

    /**
     * 删除成功后给客户端返回<code>SUCCES_DEL删除成功和额外</code>的消息
     * 
     * @param extraMsg
     *            额外的信息
     */
    public AjaxReturnInfo deleteSuccess(String extraMsg) {
        return setOpratedStatusMsg(TRUE, SUCCES_DEL, extraMsg);
    }

    public AjaxReturnInfo deleteFail() {
        return setOpratedStatusMsg(FALSE, FAIL_DEL);
    }

    /**
     * 删除失败后给客户端返回<code>FAIL_DEL删除失败和额外</code>的消息.
     * 
     * @param extraMsg
     *            额外的信息
     * @return 一个com.htsc.dsp.base.AjaxReturnInfo对象
     */
    public AjaxReturnInfo deleteFail(String extraMsg) {
        return setOpratedStatusMsg(FALSE, FAIL_DEL, extraMsg);
    }

    /**
     * 18651902506 取得成功状态的返回对象
     * 
     * @return success
     * @param message
     */
    public static AjaxReturnInfo success(String message) {
        AjaxReturnInfo ret = new AjaxReturnInfo(TRUE);
        ret.message = StringUtils.hasText(message) ? message : "success";
        return ret;
    }

    /**
     * 取得失败状态的返回对象
     * 
     * @return failed对象
     * @param message
     */
    public static AjaxReturnInfo failed(String message) {
        AjaxReturnInfo ret = new AjaxReturnInfo(FALSE);
        ret.message = StringUtils.hasText(message) ? message : "failed";
        return ret;
    }

    /**
     * 返回easyUI datagrid 【不分页】显示所需要的JSON格式数据
     * @see #listResult(List)
     * @param num
     * @param list
     * @return
     */
    @Deprecated
    public static Map<String, Object> setTable(Integer num, List<?> list) {
        Map<String, Object> json = new HashMap<String, Object>(2);
        json.put("total", num);// total键 存放总记录数，必须的
        json.put("rows", list);// rows键 存放所有的记录（客户端datagrid不分页显示）
        return json;
    }

    /**
     * 返回easyUI datagrid 异常返回的JSON格式数据.
     * @see #listResult(List, String)
     * @param num
     * @param list
     * @return
     */
    @Deprecated
    public static Map<String, Object> setTableMessage(Integer num,
            List<?> list, String message) {
        Map<String, Object> json = new HashMap<String, Object>(3);
        json.put("total", num);// total键 存放总记录数，必须的
        json.put("rows", list);// rows键 存放每页记录 list
        json.put("message", message);//
        return json;

    }

    /**
     * 取得成功标志.
     * 
     * @return 'true' or 'false'
     */
    public String getSuccess() {
        return success;
    }

    /**
     * 添加数据.
     * 
     * @param key
     *            Key值
     * @param obj
     *            对象
     */
    public AjaxReturnInfo add(String key, Object obj) {
        if (!StringUtils.hasText(key) || obj == null){
            return this;
        }
        this.datas.put(key, obj);
        return this;
    }

    /**
     * 获得返回信息.
     * 
     * @return 返回信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 取得所有数据.
     * 
     * @return 数据集合
     */
    public Map<String, Object> getDatas() {
        return datas;
    }

    /**
     * 设置后台产生的异常.
     * 
     * @param exp
     *            异常
     */
    public void setException(Exception exp) {
        exception = exp;
    }

    /**
     * 获取后台产生的异常.
     * 
     * @return 异常对象
     */
    public Exception getException() {
        return exception;
    }
}
