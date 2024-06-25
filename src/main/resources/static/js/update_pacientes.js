$(document).ready(function() {
    $('#update_paciente_form').submit(function(event) {
        event.preventDefault();

        var pacienteId = $('#paciente_id').val();
        var nombre = $('#nombre').val();
        var apellido = $('#apellido').val();
        var cedula = $('#cedula').val();

        var formData = {
            id: pacienteId,
            nombre: nombre,
            apellido: apellido,
            cedula: cedula
        };

        $.ajax({
            url: '/pacientes/' + pacienteId,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                alert('Paciente actualizado correctamente.');
                $('#div_paciente_updating').hide();
                getPacientes(); // Recargar la lista de pacientes actualizada
            },
            error: function() {
                alert('Error al actualizar el paciente.');
            }
        });
    });

    // Aquí podrías agregar lógica para llenar el formulario de actualización al hacer clic en el botón de actualizar
    $(document).on('click', '.btn-update', function() {
        var pacienteId = $(this).data('id');

        // Lógica para obtener los detalles del paciente y llenar el formulario
        $.ajax({
            url: '/pacientes/' + pacienteId,
            type: 'GET',
            success: function(response) {
                $('#paciente_id').val(response.id);
                $('#nombre').val(response.nombre);
                $('#apellido').val(response.apellido);
                $('#cedula').val(response.cedula);
                $('#div_paciente_updating').show();
            },
            error: function() {
                alert('Error al obtener los detalles del paciente.');
            }
        });
    });
});