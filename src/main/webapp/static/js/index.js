// JavaScript Document

$(function(){

    var _$recommendList = $('#recommendList');

    init();

    function init()
    {
        resizeWin();

        $(window).resize(function(e){

            resizeWin();

        });
    }

    function resizeWin()
    {
        $('.box', _$recommendList).height($('.box:first', _$recommendList).width());
        _$recommendList.addClass('loaded');
    }

});

//用UTF-8