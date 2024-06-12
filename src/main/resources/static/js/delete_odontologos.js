function deleteBy(id) {
    const url = '/odontologos/' + id;
    const settings = {
        method: 'DELETE'
    };

    fetch(url, settings)
    .then(response => {
        if (response.ok) {
            document.getElementById("tr_" + id).remove();
            alert("Odontólogo eliminado con éxito");
        } else {
            alert("Error al eliminar el odontólogo");
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Error al eliminar el odontólogo");
    });
}