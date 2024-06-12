function findBy(id) {
    const url = '/odontologos/' + id;
    const settings = {
        method: 'GET'
    };

    fetch(url, settings)
    .then(response => response.json())
    .then(data => {
        // Aquí llenarías un formulario de actualización con los datos del odontólogo
        document.getElementById('update_id').value = data.id;
        document.getElementById('update_matricula').value = data.matricula;
        document.getElementById('update_nombre').value = data.nombre;
        document.getElementById('update_apellido').value = data.apellido;
    });
}

function updateOdontologo() {
    const id = document.getElementById('update_id').value;
    const url = '/odontologos/' + id;
    const settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            matricula: document.getElementById('update_matricula').value,
            nombre: document.getElementById('update_nombre').value,
            apellido: document.getElementById('update_apellido').value
        })
    };

    fetch(url, settings)
    .then(response => response.json())
    .then(data => {
        alert("Odontólogo actualizado con éxito");
        // Aquí puedes actualizar la fila en la tabla si es necesario
    });
}