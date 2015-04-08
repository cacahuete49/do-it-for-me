
// Tableau de memorisation des notes pour chaque liste
var ArrListeEtoile = new Object();

//-------------------------------------------------------
// Gestion de la visibilite des etoiles au survol
//-------------------------------------------------------
function GestionHover(idListe, indice, nbEtoile){
	for (i=1; i<= nbEtoile; i++)
	{
		var idoff = "staroff-" + idListe + "-" + i;
		var idon = "staron-" + idListe + "-" + i;
		
		if(indice == -1)
		{
			// Sortie du survol de la liste des etoiles
			if (ArrListeEtoile[idListe] >= i){
				document.getElementById(idoff).style.display ="none";
				document.getElementById(idon).style.display ="block";
			}
			else{
				document.getElementById(idoff).style.display ="block";
				document.getElementById(idon).style.display ="none";
			}
		}
		else
		{
			// Survol de la liste des etoiles
			if(i <= indice){
				document.getElementById(idoff).style.display ="none";
				document.getElementById(idon).style.display ="block";
			}
			else{
				document.getElementById(idoff).style.display ="block";
				document.getElementById(idon).style.display ="none";
			}
		}
	}
}

//-------------------------------------------------------
// Selection d une note pour une liste
//-------------------------------------------------------
function ChoixSelection(idListe, indice, nbEtoile){
	ArrListeEtoile[idListe] = indice;
//	var score = "score-" + idListe;
	var index = parseInt(idListe.charAt(1))-1;
	document.forms[index].note.value = indice;
//	document.getElementById(score).innerHTML = " " + indice + "/" + nbEtoile;
	submitFormulaire(index);
}

function submitFormulaire(index) {
	document.forms[index].submit();
}

//-------------------------------------------------------
// Creation d une liste de notation unique
//-------------------------------------------------------
function CreateListeEtoile(idListe, indice , nbEtoile){
	ArrListeEtoile[idListe] = 0;

	var renduListe = "";
	renduListe += "<div class=\"listeEtoile\" onmouseout=\"GestionHover('" + idListe + "','"+ indice +"', '" + nbEtoile + "')\">";
	renduListe += "<ul>";
	
	for(i=1; i<=nbEtoile; i++){
		renduListe += "<li>";
		renduListe += "<a href=\"javascript:ChoixSelection('" + idListe + "', '" + i + "', '" + nbEtoile + "')\" onmouseover=\"GestionHover('" + idListe + "', '" + i + "', '" + nbEtoile + "')\">";
		renduListe += "<img id=\"staroff-" + idListe + "-" + i + "\" src=\"resources/img/staroff.gif\" border=\"0\" title=\"" + i + "\" style=\"border-width: 0px; display: block;\">";
		renduListe += "<img id=\"staron-" + idListe + "-" + i + "\" src=\"resources/img/staron.gif\" border=\"0\" title=\"" + i + "\" style=\"border-width: 0px; display: none;\">";
		renduListe += "</a>";
		renduListe += "</li>";
	}
	
	renduListe += "	</ul>";
	renduListe += "</div>";
	renduListe += "<label id=\"score-" + idListe + "\"></label>";
	
	document.getElementById(idListe).outerHTML = renduListe;
}
