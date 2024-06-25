function deleteBy(id) {
    const url = `/pacientes/${id}`;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
        .then(response => {
            if (response.ok) {
                console.log(`Paciente con id ${id} eliminado`);
                document.getElementById(`tr_${id}`).remove();
            } else {
                console.error('Error al eliminar paciente:', response);
            }
        })
        .catch(error => console.error('Error al eliminar paciente:', error));
}