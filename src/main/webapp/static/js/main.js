// JavaScript Document

$(function(){

    try{
        init();
    }
    catch (e){}

    function init()
    {
        initMenu();

        initSearchHead();
        initSearchHome();
    }

    function initMenu()
    {
        if(!__curPage) return;

        var $menuHead = $('#menuHead');
        $('.'+__curPage, $menuHead).addClass('on');
    }

    function initSearchHead()
    {
        var $search = $('#searchHead');
        if(!$search.length) return;

        $(".sel", $search).change(function(e){

            var txt = $(this).val();
            $(".txt", $search).text(txt);

        });
        $(".sel", $search).change();
    }
    
    function initSearchHome()
    {
        var $search = $('#searchHome');
        if(!$search.length) return;

        $(".sel", $search).change(function(e){

            var txt = $(this).val();
            $(".txt", $search).text(txt);

        });
        $(".sel", $search).change();
    }

});



//用UTF-8