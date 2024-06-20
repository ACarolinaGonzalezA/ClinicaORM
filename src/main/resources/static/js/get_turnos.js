window.addEventListener('load', function () {
    (function(){

      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         for(turno of data){
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = turno.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                                '<td class=\"td_idPaciente\">' + turno.paciente.nombre.toUpperCase() + '</td>' + // Usa el campo correcto de la entidad paciente
                                '<td class=\"td_idOdontologo\">' + turno.odontologo.nombre.toUpperCase() + '</td>' + // Usa el campo correcto de la entidad odontologo
                                '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                                '<td>' + deleteButton + '</td>'; // Asegúrate de que este cierre está correctamente

        };

    });
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_turno.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })