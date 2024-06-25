function deletePaciente(id) {
   if (confirm('¿Estás seguro que deseas eliminar este paciente?')) {
           $.ajax({
               url: 'http://localhost:8080/pacientes/' + id,
               type: 'DELETE',
               success: function(response) {
                   alert('Paciente eliminado correctamente.');
                   getPacientes();
               },
               error: function() {
                   alert('Error al eliminar el paciente.');
               }
           });
       }
}