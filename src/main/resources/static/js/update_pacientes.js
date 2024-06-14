window.addEventListener('load', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const pacienteId = urlParams.get('id');

    fetch(`/pacientes/buscar/id/${pacienteId}`)
    .then(response => response.json())
    .then(paciente => {
        document.getElementById('paciente_id').value = paciente.id;
        document.getElementById('nombre').value = paciente.nombre;
        document.getElementById('apellido').value = paciente.apellido;
        document.getElementById('cedula').value = paciente.cedula;
        document.getElementById('email').value = paciente.email;
    });

    const formulario = document.querySelector('#updatePacienteForm');

    formulario.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = {
            id: document.getElementById('paciente_id').value,
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value,
            cedula: document.getElementById('cedula').value,
            email: document.getElementById('email').value
        };

        fetch('/pacientes', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('Paciente actualizado con éxito');
                window.location.href = './get_pacientes.html';
            } else {
                alert('Error al actualizar el paciente');
            }
        });
    });
});