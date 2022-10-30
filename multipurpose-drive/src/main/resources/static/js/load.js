     // remove feedback message after 5 secs
       $(document).ready(function(){
       var boxes = document.querySelectorAll('.msgAlert');;
         setInterval(() => {

        boxes.forEach(box =>
        box.style.visibility = 'visible' ? box.style.visibility = 'hidden' : box.style.visibility = 'hidden' )

}, 5000);

// redirect to last tab.
            $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
                localStorage.setItem('activeTab', $(e.target).attr('href'));
            });
            var activeTab = localStorage.getItem('activeTab');
            if(activeTab){
                $('#nav-tab a[href="' + activeTab + '"]').tab('show');
            } else {
                $('#nav-tab a[href="' + '#nav-files' + '"]').tab('show');
            }
        });

// For opening the credentials modal
                    function showCredentialModal(credentialId, url, username, password) {
                        $('#credential-id').val(credentialId ? credentialId : '');
                        $('#credential-url').val(url ? url : '');
                        $('#credential-username').val(username ? username : '');
                        $('#credential-password').val(password ? password : '');
                        $('#credentialModal').modal('show');
                    }

                    $(document).ready(function(){
                        $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
                            localStorage.setItem('activeTab', $(e.target).attr('href'));
                        });
                        var activeTab = localStorage.getItem('activeTab');
                        if(activeTab){
                            $('#myTab a[href="' + activeTab + '"]').tab('show');
                        }
                    });
                                // For opening the note modal
                                function showNoteModal(noteId, noteTitle, noteDescription) {
                                    $('#note-id').val(noteId ? noteId : '');
                                    $('#note-title').val(noteTitle ? noteTitle : '');
                                    $('#note-description').val(noteDescription ? noteDescription : '');
                                    $('#noteModal').modal('show');
                                }
