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

/**
 * 自定义的验证
 */
$.extend($.fn.validatebox.defaults.rules, {
    isPhone: {
        //value，是input的值,param[0]是我传入的验证规则长度
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: 'Please enter at least {0} characters.'
    }
});
