$(function () {
    $.extend($.fn.validatebox.defaults.rules, {
        equalss: {
            validator: function (value, param) {
                return value == $(param[0]).val();
            },
            message: 'Field do not match.'
        },
        auth: {
            validator: function (value, param) {
                return value == $(param[0]).val();
            },
            message: 'Field do not match.'
        }

    });


    $.extend($.fn.validatebox.defaults.rules, {
        selectValueRequired: {
            validator: function(value,param){
                console.info($(param[0]).find("option:contains('"+value+"')").val());
                return $(param[0]).find("option:contains('"+value+"')").val() != '';
            },
            message: 'select value required.'
        }
    });

});