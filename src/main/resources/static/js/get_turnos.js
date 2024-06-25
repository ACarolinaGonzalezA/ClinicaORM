$(document).ready(function() {
    getTurnos();
});

function getTurnos() {
    $.ajax({
        url: 'http://localhost:8080/turnos',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            var turnos = response;
            var turnoTableBody = $('#turnoTableBody');
            turnoTableBody.empty();
            $.each(turnos, function(index, turno) {
                turnoTableBody.append(`
                    <tr>
                        <td>${turno.id}</td>
                        <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                        <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                        <td>${turno.fecha}</td>
                        <td>
                            <button class="btn btn-danger" onclick="deleteTurno(${turno.id})">Eliminar</button>
                            <button class="btn btn-primary btn-update" data-id="${turno.id}" data-fecha="${turno.fecha}" data-idPaciente="${turno.idPaciente}" data-idOdontologo="${turno.idOdontologo}">Actualizar</button>
                        </td>
                    </tr>
                `);
            });
        },
        error: function() {
            alert('Error al cargar los turnos.');
        }
    });
}
$(document).on('click', '.btn-update', function() {
    var turnoId = $(this).data('id');
    var fecha = $(this).data('fecha');
    var idPaciente = $(this).data('idPaciente');
    var idOdontologo = $(this).data('idOdontologo');

    openUpdateForm(turnoId, fecha, idPaciente, idOdontologo);
});
