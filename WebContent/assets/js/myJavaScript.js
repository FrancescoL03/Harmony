	//--------- BORDI PER ELEMENTI DEI FORM  ---------
var borderOK = '2px solid #080';
var borderNO = '2px solid #f00';

	//--------- VALIDAZIONE REGISTRAZIONE ---------
var passwordOK = false;
var cognomeOK = false;
var nomeOK = false;
var emailOK = false;

	function validaNome(){
		var input = document.forms['registrazione']['nome'];
		if (input.value.trim().length > 0 && input.value.trim().length <=30 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff\.\,\-]+$/)) {
			input.style.border = borderOK;
			nomeOK = true;
		} else {
			input.style.border = borderNO;
			nomeOK = false;
		}
		cambiaStatoRegistrami();
	}
	
	function validaCognome(){
		var input = document.forms['registrazione']['cognome'];
		if (input.value.trim().length > 0 && input.value.trim().length <=30 && input.value.match(/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/)) {
			input.style.border = borderOK;
			cognomeOK = true;
		} else {
			input.style.border = borderNO;
			cognomeOK = false;
		}
		cambiaStatoRegistrami();
	}

	function validaEmail(){
		var input = document.forms['registrazione']['email'];
		if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/) && input.value.trim().length <=50) {
			
			//Verifico che non esista nessun utente registrato con la stessa email
			var xmlHttpReq = new XMLHttpRequest();
			xmlHttpReq.onreadystatechange = function() {
				
				if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
					document.getElementById("used").innerHTML = "";
					emailOK = true;
					input.style.border = borderOK;
				} else {
					if(this.responseText == '<used/>'){
						document.getElementById("used").innerHTML = "Email già usata.";
					}
					input.style.border = borderNO;
					emailOK = false;
				}
				cambiaStatoRegistrami();
			}
			xmlHttpReq.open("GET", "VerificaEmail?email=" + encodeURIComponent(input.value), true);
			xmlHttpReq.send();
		} else {
			input.style.border = borderNO;
			emailOK = false;
			cambiaStatoRegistrami();
		}
	}
	
	function validaPassword() {
		var inputpw = document.forms['registrazione']['password'];
		var inputpwconf = document.forms['registrazione']['passwordConferma'];
		var password = inputpw.value;
		if (password.length >= 8 && password.length<=30 && password.toUpperCase() != password
				&& password.toLowerCase() != password && /[0-9]/.test(password)) {
			inputpw.style.border = borderOK;

			if (password == inputpwconf.value) {
				inputpwconf.style.border = borderOK;
				passwordOK = true;
			} else {
				inputpwconf.style.border = borderNO;
				passwordOK = false;
			}
		} else {
			inputpw.style.border = borderNO;
			inputpwconf.style.border = borderNO;
			passwordOK = false;
		}
		cambiaStatoRegistrami();
	}
	
	function cambiaStatoRegistrami() {
		if (emailOK && passwordOK && nomeOK && emailOK) {
			document.getElementById('registrami').disabled = false;
			document.getElementById('registrami').style.backgroundColor = "green";
			document.getElementById('registrami').style.borderColor = "green";
			document.getElementById('registramimessaggio').innerHTML = '';
		} else {
			document.getElementById('registrami').disabled = true;
			document.getElementById('registrami').style.backgroundColor = "red";
			document.getElementById('registrami').style.borderColor = "red";
			document.getElementById('registramimessaggio').innerHTML = 'Assicurati di compilare tutti i campi correttamente.';
		}
	}
	

	//--------- VALIDAZIONE CHECKOUT ---------
	
var cartaOK = false;
var telefonoOK = false;
var indirizzoOK = false;
	
	function validaCarta(){
		var carta = document.forms["checkout"]["numeroDiCarta"];
		if(carta.value.length == 16 && carta.value.match(/^[0-9]{16}$/)){
			cartaOK = true;
			carta.style.border = borderOK;
		} else {
			cartaOK = false;
			carta.style.border = borderNO;
		}
		aggiornaPaga();
	}
	
	function validaTelefono(){
		var telefono = document.forms["checkout"]["telefono"];
		if(telefono.value.length == 10 && telefono.value.match(/^[0-9]{10}$/)){
			telefonoOK = true;
			telefono.style.border = borderOK;
		} else {
			telefonoOK = false;
			telefono.style.border = borderNO;
		}
		aggiornaPaga();
	}
	
	function validaIndirizzo(){
		var indirizzo = document.forms["checkout"]["indirizzo"];
		if(indirizzo.value.trim().length > 0 && indirizzo.value.trim().length <100 && indirizzo.value.match(/^[a-zA-Z]{1,}[\,]{1}([\ ]{1,})?[a-zA-z]{1,}[\ ]{1,}([a-zA-z]{1,}[\ ]*)+([0-9]{1,})*$/)){
			indirizzoOK = true;
			indirizzo.style.border = borderOK;
		} else {
			indirizzoOK = false;
			indirizzo.style.border = borderNO;
		}
		aggiornaPaga();
	}
	
	function aggiornaPaga(){
		if(indirizzoOK && telefonoOK && cartaOK){
			document.forms["checkout"]["paga"].disabled = false;
			document.forms["checkout"]["paga"].style.backgroundColor = "green";
			document.forms["checkout"]["paga"].style.borderColor = "green";
			document.getElementById('pagaMessaggio').innerHTML = '';
		} else {
			document.forms["checkout"]["paga"].disabled = true;
			document.forms["checkout"]["paga"].style.backgroundColor = "red";
			document.forms["checkout"]["paga"].style.borderColor = "red";
			document.getElementById('pagaMessaggio').innerHTML = 'Assicurati di compilare tutti i campi correttamente.';
		}
	}
	
	//--------- VALIDAZIONE MODIFICA ACCOUNT ---------

	var passwordModOK = false;
	var cognomeModOK = false;
	var nomeModOK = false;

		function validaNomeMod(){
			var input = document.forms['profiloModifica']['nome'];
			if (input.value.trim().length > 0 && input.value.trim().length <=30 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff\.\,\-]+$/)) {
				input.style.border = borderOK;
				nomeModOK = true;
			} else {
				input.style.border = borderNO;
				nomeModOK = false;
			}
			cambiaStatoRegistramiMod();
		}
		
		function validaCognomeMod(){
			var input = document.forms['profiloModifica']['cognome'];
			if (input.value.trim().length > 0 && input.value.trim().length <=30 && input.value.match(/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/)) {
				input.style.border = borderOK;
				cognomeModOK = true;
			} else {
				input.style.border = borderNO;
				cognomeModOK = false;
			}
			cambiaStatoRegistramiMod();
		}

		function validaPasswordMod() {
			var inputpw = document.forms['profiloModifica']['password'];
			var inputpwconf = document.forms['profiloModifica']['passwordConferma'];
			var password = inputpw.value;
			if (password.length >= 8 && password.length<=30 && password.toUpperCase() != password
					&& password.toLowerCase() != password && /[0-9]/.test(password)) {
				inputpw.style.border = borderOK;

				if (password == inputpwconf.value) {
					inputpwconf.style.border = borderOK;
					passwordModOK = true;
				} else {
					inputpwconf.style.border = borderNO;
					passwordModOK = false;
				}
			} else {
				inputpw.style.border = borderNO;
				inputpwconf.style.border = borderNO;
				passwordModOK = false;
			}
			cambiaStatoRegistramiMod();
		}
		
		function cambiaStatoRegistramiMod() {
			if ( passwordModOK && nomeModOK && cognomeModOK) {
				document.getElementById('aggiornami').disabled = false;
				document.getElementById('aggiornami').style.backgroundColor = "green";
				document.getElementById('aggiornami').style.borderColor = "green";
				document.getElementById('aggiornamimessaggio').innerHTML = '';
			} else {
				document.getElementById('aggiornami').disabled = true;
				document.getElementById('aggiornami').style.backgroundColor = "red";
				document.getElementById('aggiornami').style.borderColor = "red";
				document.getElementById('aggiornamimessaggio').innerHTML = 'Assicurati di compilare tutti i campi correttamente.';
			}
		}
	
	//--------- VERIFICA UTENTE LOGGATO ---------
	
	function checkIsLogged(){
		var x = $("#utente").val();

		if(x==""){
			alert("Devi effettuare il login prima procedere al checkout.");
			$("#LoginModal").modal("show");
			return false;
		}
		else return true;
	}
	
	//--------- SUGGERIMENTI RICERCA ---------

	function loadSuggerimenti(input){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function(){
			if(this.readyState==4 && this.status==200){
				carica(this);
			}
		}
		xhttp.open("GET", "Suggerimenti?input="+input, true);
		xhttp.send();
	}
	
	function carica(a){
			var obj = JSON.parse(a.responseText);
			var suggerimenti;
			for(i=0; i<obj.length;i++){
				suggerimenti += "<option>" + obj[i].name + "</option>";
			}
			document.getElementById("product").innerHTML = suggerimenti;
	}
	