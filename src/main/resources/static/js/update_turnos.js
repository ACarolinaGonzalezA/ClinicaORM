function openUpdateForm(turnoId, fecha, idPaciente, idOdontologo) {
    // Lógica para abrir el formulario de actualización y rellenar los campos
    $('#turno_id').val(turnoId);
    $('#fecha').val(fecha);
    $('#idPaciente').val(idPaciente);
    $('#idOdontologo').val(idOdontologo);

    // Mostrar el formulario de actualización si está oculto
    $('#div_turno_updating').show();
}

$(document).ready(function() {
    $('#update_turno_form').submit(function(event) {
        event.preventDefault();

        var turnoId = $('#turno_id').val();
        var fecha = $('#fecha').val();
        var idPaciente = $('#idPaciente').val();
        var idOdontologo = $('#idOdontologo').val();

        var formData = {
            fecha: fecha,
            paciente: {
                id: idPaciente
            },
            odontologo: {
                id: idOdontologo
            }
        };

        $.ajax({
            url: 'http://localhost:8080/turnos/' + turnoId,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                alert('Turno actualizado correctamente.');
                $('#div_turno_updating').hide();
                getTurnos(); // Actualizar lista de turnos después de la actualización
            },
            error: function() {
                alert('Error al actualizar el turno.');
            }
        });
    });
});