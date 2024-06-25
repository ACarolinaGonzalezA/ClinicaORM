function getPacientes() {
    const url = '/pacientes';
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            const pacienteTableBody = document.getElementById("pacienteTableBody");
            pacienteTableBody.innerHTML = ''; // Limpiar contenido anterior si es necesario

            data.forEach(paciente => {
                const pacienteRow = document.createElement('tr');
                pacienteRow.innerHTML = `
                    <td>${paciente.id}</td>
                    <td>${paciente.nombre.toUpperCase()}</td>
                    <td>${paciente.apellido.toUpperCase()}</td>
                    <td>${paciente.cedula.toUpperCase()}</td>
                    <td>${paciente.fechaIngreso}</td>
                    <td>${paciente.domicilio.calle.toUpperCase()}</td>
                    <td>${paciente.email.toUpperCase()}</td>
                    <td>
                        <button type="button" class="btn btn-info btn-update" data-id="${paciente.id}">Actualizar</button>
                        <button type="button" class="btn btn-danger btn-delete" onclick="deletePaciente(${paciente.id})">Eliminar</button>
                    </td>
                `;
                pacienteTableBody.appendChild(pacienteRow);
            });
        })
        .catch(error => {
            console.error('Error al cargar pacientes:', error);
        });
}

// Llamar a getPacientes cuando se carga la página
window.addEventListener('load', getPacientes);