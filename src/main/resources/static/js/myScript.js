/**
 * 
 */
$(function() {
	//alert("la fonction s'appelle ici");
	$('.panel-heading span.clickable').parents('.panel-menu').find('.panel-body').slideUp();
	$('.panel-heading span.clickable').addClass('panel-collapsed');
	$('.panel-heading span.clickable').find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
	$('.panel-heading span.clickable').parents('.panel-form').find('.heading-form').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
	$('.panel-heading span.clickable').parents('.panel-form').find('.heading-form').removeClass('panel-collapsed')
$(document).on('click', '.panel-heading span.clickable', function(e){
    var $this = $(this);
	if(!$this.hasClass('panel-collapsed')) {
		$this.parents('.panel-menu').find('.panel-body').slideUp();
		$this.parents('.panel-form').find('.panel-body').slideUp();
		$this.addClass('panel-collapsed');
		$this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
	} else {
		$this.parents('.panel-menu').find('.panel-body').slideDown();
		$this.parents('.panel-form').find('.panel-body').slideDown();
		$this.removeClass('panel-collapsed');
		$this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
	}
});
});
/*function enreg(){
	alert("ici");
}*/

function mykeyPress(e){
	//alert("bien "+$('#t').val());
	/*var keynum;
	if(window.event){
		keynum = e.keyCode;
	}
	else if(e.key){
		keynum = e.key;
	}
	
	*/
	//e.which = le code de la touche qui a été saisi. 9 = touche tabulation
	//e.key = donne en chaine de caractère la touche qui a été saisi par exemple Tab pour la touche tabulation. 
	var keynum = (e.which)?e.which:e.keyCode;
	if(keynum == 13){
		//alert("on a appuyer la touche de code "+keynum+"  et on soumet ainsi la valeur: "+$('#t').val());
	}
	/*alert("keynum == "+keynum);
	if(keynum == "Tab") alert("tabulation");
	var charStr = String.fromCharCode(keynum);
	alert("charStr == "+charStr);*/
	
}

function returnfalseifTab(event){
	alert("on key press");
}

//$(function() {
	/*function submitClasseConcerne(){
		alert("je vous comprend tres bien");*/
		//var text = document.getElementById('idClasse').selectedIndex;
		/*var text = $('#idClasse option:selected').val();
		alert("texte== "+text);*/
		/*
		$('#form').submit();
	}*/
	
	/*function modifierLienRecherche(){
		var text = $('#idClasse option:selected').val();
		var href= $('#lienRecherche').attr('href');
		alert("idClasse select== "+text+" valeurHref== "+href);
	}*/
	
//});
