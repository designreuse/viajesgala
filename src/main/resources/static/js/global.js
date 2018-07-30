var j = jQuery.noConflict();
j(document).ready(function() {

	//Cookies
	var cookieshtml = '<div class="fondocookies"><div class="cookies"><div class="main fila pdd80"><div class="columna10 pdd15 tacenter"><p>Al continuar navegando por este sitio web aceptas nuestra <a href="/politica-de-privacidad.html">política de privacidad</a>.</p></div><div class="columna10 fila"><div class="columna5 tacenter pdd15"><span class="btn btn-primary aceptar">Aceptar</span></div><div class="columna5 tacenter pdd15"><a href="https://www.google.com/" class="btn btn-primary rechazar">Abandonar página</a></div></div></div></div></div>';
    if (j.cookie("cookies_aceptacion") != 1)
    {
        j("body").prepend(cookieshtml);
    }
    
    j(".cookies span.aceptar").click(function(){
        j.cookie('cookies_aceptacion', '1', { expires: 365, path: '/' });
        j(".fondocookies").remove();
    });

    new WOW().init();

    j("#sticker").sticky({topSpacing:0});

    j('.menu').sidr({
        source: 'header .moduletable_menu',
        side: 'left',
        renaming: false,
        speed: 600,
        onOpen: function(){j("header").addClass("activo");},
        onClose: function(){j("header").removeClass("activo");}
    });


    //Variables
    var heightScreen = j(window).height();
    var widthScreen = screen.width;
    var heightHeader = j("header").outerHeight();
    var heightMain = heightScreen - heightHeader;
    var heightFooter = j("footer").height();

    //Resize
    j(".hscreen").css("height",heightMain);

    j( window ).resize(function() {
        //Variables
        heightScreen = j(window).height();
        widthScreen = screen.width;
        heightHeader = j("header").outerHeight();
        heightMain = heightScreen - heightHeader;
        heightFooter = j("footer").height();

        j(".hscreen").css("height",heightMain);
    });

    //Mail
    var m1 = "mailto";
    var m2 = ":";
    var m3 = "nombre";
    var m4 = "@";
    var m5 = "dominio";
    var m6 = ".tld";

    j(".hrefmail").attr("href",m1+m2+m3+m4+m5+m6);
    j(".txtmail").text(m3+m4+m5+m6);

    //Ancla
    j(".ancla").click(function(){
        var destino = j(this).attr("data-ancla");
        hacerAncla(destino, heightHeader);
    });

    function hacerAncla(destino, resto)
    {
        j('html, body').animate({
            scrollTop: j(destino).offset().top
        }, 800);
    }

    //#################################//
    var sonando = true;
    j(".silenciar").click(function(){
        if (sonando)
        {
            j("audio").prop('muted', true);
            j("i", this).removeClass("fa-volume-up");
            j("i", this).addClass("fa-volume-off");
        }
        else
        {
            j("audio").prop('muted', false);
            j("i", this).removeClass("fa-volume-off");
            j("i", this).addClass("fa-volume-up");
        }
        sonando = !sonando;
    });        
    if (screen.width > 915){
        var mySwiper = new Swiper ('.swiper-container', {
            direction: 'vertical',
            loop: false,
            autoHeight: true,
            pagination: '.swiper-pagination',
            paginationClickable: true
        });
    }
});