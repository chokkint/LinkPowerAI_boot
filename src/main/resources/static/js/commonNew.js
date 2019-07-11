function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/jquery-2.2.3.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/jquery-ui-1.10.2.custom.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/easyui/jquery.easyui.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/ConstantNew.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/utilsNew.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/uiNew.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/uiNew2.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/commonUtils.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/json2.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/jquery.ztree.all-3.5.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/validate/jquery.validate.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/js/framework/validate/jquery.validate.setting.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/bootstrap/js/bootstrap.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/jQueryUI/jquery-ui.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/iCheck/icheck.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/input-mask/jquery.inputmask.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/input-mask/jquery.inputmask.date.extensions.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/input-mask/jquery.inputmask.extensions.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/iCheck/icheck.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/datepicker/bootstrap-datepicker.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/colorpicker/bootstrap-colorpicker.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/timepicker/bootstrap-timepicker.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/slimScroll/jquery.slimscroll.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/fastclick/fastclick.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/dist/js/app.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/dist/js/demo.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/adminLTE/plugins/moment/2.11.2/moment.min.js'></script>");

//SweetAlert2
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/sweetalert2/promise.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/sweetalert2/sweetalert2.all.min.js'></script>");

//toastr
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/toastr/toastr.js'></script>");

//bootstrap-table
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/bootstrap-table/bootstrap-table.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/bootstrap-table/locale/bootstrap-table-zh-CN.js'></script>");

//bootstrap-select
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/bootstrap-select/bootstrap-select.min.js'></script>");

//bootstrap-table-export
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/bootstrap-table/extensions/export/bootstrap-table-export.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/tableExport/tableExport.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/tableExport/libs/FileSaver/FileSaver.min.js'></script>");
document.writeln("<script type='text/javascript' src='"+getContextPath()+"/plug_in/tableExport/libs/js-xlsx/xlsx.core.min.js'></script>");