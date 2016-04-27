/*       $.fn.extend({
 min: function (a, b) {
 return a < b ? a : b;
 },
 max: function (a, b) {
 return a > b ? a : b;
 }
 });
 //定义和使用方法
 alert($.fn.min(5, 4));*/
//验证手机号码的正则表达式
var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
function validPhone(phoneNum) {

    if (reg.test(phoneNum)) {
        return true;
    } else {
        return false;
    }
}
/**
 * 自定义的验证
 */
$.extend($.fn.validatebox.defaults.rules, {
    isPhone: {
        //value，是input的值,param[0]是我传入的验证规则长度
        validator: function (value, param) {
            return validPhone(value);
        },
        message: resource.validForm.phoneMsg
    }
});
