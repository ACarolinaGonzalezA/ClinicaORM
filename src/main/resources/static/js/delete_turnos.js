function deleteBy(id) {
    const url = '/turnos/' + id;
    const settings = {
        method: 'DELETE'
    }

    fetch(url, settings)
    .then(response => {
        if (response.ok) {
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
        } else {
            console.error('Error al eliminar el turno:', response.status);
        }
    })
    .catch(error => {
        console.error('Error al eliminar el turno:', error);
    });
}