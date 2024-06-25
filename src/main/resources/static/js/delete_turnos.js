function deleteTurno(id) {
    if (confirm('¿Estás seguro que deseas eliminar este turno?')) {
        $.ajax({
            url: 'http://localhost:8080/turnos/' + id,
            type: 'DELETE',
            success: function(response) {
                alert('Turno eliminado correctamente.');
                getTurnos();
            },
            error: function() {
                alert('Error al eliminar el turno.');
            }
        });
    }
}