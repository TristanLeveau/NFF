function AccesProtege() {
    self.location.href=prompt("Mot de passe :","");
}

function open_infos()
{
    width = 300;
    height = 200;
    if(window.innerWidth)
    {
        var left = (window.innerWidth-width)/2;
        var top = (window.innerHeight-height)/2;
    }
    else
    {
        var left = (document.body.clientWidth-width)/2;
        var top = (document.body.clientHeight-height)/2;
    }
    window.open('livraisondetail','Détails de la livraison','menubar=yes, scrollbars=yes, top='+top+', left='+left+', width='+width+', height='+height+'');
}