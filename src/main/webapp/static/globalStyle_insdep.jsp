<%@include file="uniform.jsp"%>

<!--insdep
#reset.min.css与normalize.min.css是两个css重置样式，你可以2者选其一或引用自己编写的重置样式。（可选）
区别:
1、reset.min.css是将所有的浏览器的自带样式重置掉，这样更易于保持各浏览器渲染的一致性。
2、normalize.min.css是尽量保留浏览器的默认样式，精确定位需要重置的样式。-->

<link href="${ctx}/static/insdep/reset.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/insdep/normalize.min.css" rel="stylesheet" type="text/css">

<link href="${ctx}/static/insdep/icon.css" rel="stylesheet" type="text/css">

<!--#easyui_full.css是Insdep Theme全部样式包，它包含了以上全部样式（不含reset.min.css或normalize.min.css外）（可选）-->
<link href="${ctx}/static/insdep/easyui_full.css" rel="stylesheet" type="text/css">

<!--#jquery脚本库（必须）-->
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.5.2/jquery.min.js"></script>

<!--easyui 1.5.1-->
<!--#EasyUI脚本库（必须）-->
<script type="text/javascript" src="${ctx}/static/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

<!--#InsdepTheme支持脚本库，包含了中文语言包（必须）-->
<script type="text/javascript" src="${ctx}/static/insdep/insdep-extend.min.js"></script>

<!--RMS-->
<script type="text/javascript" src="${ctx}/static/app/js/uniform.js"></script>