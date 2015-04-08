<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<%@ include file="../bloc/head.jsp"%>
<head>
<title>Accueil</title>
</head>
<body>
   <%@ include file="../bloc/header.jsp"%>
   <%@ include file="../bloc/carouselle.jsp"%>

   <div class="container marketing">

      <div id="ancre_contenu" class="row featurette">
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_alarms.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Réveil</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_airplane_mode_off.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Mode Avion</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_battery.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Energie</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_bluetooth.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Bluetooth</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_chat.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Techno</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_data_usage.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Chronomètre</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_go_to_today.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Calendrier</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_headphones.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Écouteur</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_import_export.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Data</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_labels.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>NFC</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_location_found.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>GPS</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_mic.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Synthèse Vocale</h2>
         </div>

         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_network_wifi.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Wifi</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_new_event.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Notification</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_person.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Contact</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_phone.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Écran</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_play_over_video.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Musique</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_unread.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>SMS</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_web_site.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Web</h2>
         </div>
         <div class="col-lg-2">
            <img class="img-rounded" src="resources/img/ic_action_volume_on.png"
               alt="Generic placeholder image" width="96" height="96" />
            <h2>Haut Parleur</h2>
         </div>

      </div>

      <hr class="featurette-divider" />

      <div class="row">
         <div class="col-md-7">
            <h2>L'avenir</h2>
            <p>Prendre un temps d'avance</p>
         </div>
         <div class="col-md-5">
            <img class="featurette-image img-responsive center-block"
               data-src="holder.js/500x500/auto" alt="img 500x500"
               src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNTAwIiBoZWlnaHQ9IjUwMCIgdmlld0JveD0iMCAwIDUwMCA1MDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzLz48cmVjdCB3aWR0aD0iNTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjE5MC4zMjAzMTI1IiB5PSIyNTAiIHN0eWxlPSJmaWxsOiNBQUFBQUE7Zm9udC13ZWlnaHQ6Ym9sZDtmb250LWZhbWlseTpBcmlhbCwgSGVsdmV0aWNhLCBPcGVuIFNhbnMsIHNhbnMtc2VyaWYsIG1vbm9zcGFjZTtmb250LXNpemU6MjNwdDtkb21pbmFudC1iYXNlbGluZTpjZW50cmFsIj41MDB4NTAwPC90ZXh0PjwvZz48L3N2Zz4="
               data-holder-rendered="true" />
         </div>
      </div>

      <hr class="featurette-divider" />

      <div class="row featurette">
         <div class="col-md-5">
            <img class="featurette-image img-responsive center-block"
               data-src="holder.js/500x500/auto" alt="img 500x500"
               src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNTAwIiBoZWlnaHQ9IjUwMCIgdmlld0JveD0iMCAwIDUwMCA1MDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzLz48cmVjdCB3aWR0aD0iNTAwIiBoZWlnaHQ9IjUwMCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjE5MC4zMjAzMTI1IiB5PSIyNTAiIHN0eWxlPSJmaWxsOiNBQUFBQUE7Zm9udC13ZWlnaHQ6Ym9sZDtmb250LWZhbWlseTpBcmlhbCwgSGVsdmV0aWNhLCBPcGVuIFNhbnMsIHNhbnMtc2VyaWYsIG1vbm9zcGFjZTtmb250LXNpemU6MjNwdDtkb21pbmFudC1iYXNlbGluZTpjZW50cmFsIj41MDB4NTAwPC90ZXh0PjwvZz48L3N2Zz4="
               data-holder-rendered="true" />
         </div>
         <div class="col-md-7">
            <h2>Des possibilités infinies</h2>
            <p>Avec la réalisation de graphe</p>
         </div>
      </div>
   </div>
   <%@ include file="../bloc/footer.jsp"%>
</body>
</html>
