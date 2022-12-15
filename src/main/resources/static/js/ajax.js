const getJsonUsers = "/adminPage/json-users";
const token = $('#_csrf').attr('content');
const header = $('#_csrf_header').attr('content');

let userIdToDelete;
let rowIndexToDelete;

$.ajaxSetup({
    headers: {
        'Content-Type':  'application/json',
        'Accept': 'application/json',
        'X-CSRF-TOKEN': token
    }
});

function setRowIndexAndUserId(row, id) {
    userIdToDelete = id;
    rowIndexToDelete = row.parentNode.parentNode.rowIndex;
}


function closeModal(nameOfTheModal) {
    $(nameOfTheModal).modal('hide');
}

function deleteEntity() {
   
    let deleteUserUrl = '/adminPage/json-users/delete/' + userIdToDelete;

    $.ajax({
        url: deleteUserUrl,
        type: 'DELETE',
        success: function () {

            let table = $("#user-table");
            table[0].deleteRow(rowIndexToDelete);

            $('#alert-messages').append(
                "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"+
                "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"+
                "Usuário <strong>deletado</strong> com sucesso!"+
                "</div>"
            );
            closeModal('#deleteModal');
            userIdToDelete = "";
            rowIndexToDelete = "";
        }
    });
}

function deleteEntityMarca() {
   
    let deleteUserUrl = '/marcas/' + userIdToDelete;

    $.ajax({
        url: deleteUserUrl,
        type: 'DELETE',
        success: function () {

            $("#deleta-card-id-" + userIdToDelete).remove();
            //let table = $("#tabela-marca");
            //table[0].deleteRow(rowIndexToDelete);

            $('#alert-messages').append(
                "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"+
                "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"+
                "Marca <strong>deletado</strong> com sucesso!"+
                "</div>"
            );
            closeModal('#delete-marca-modal');
            userIdToDelete = "";
            rowIndexToDelete = "";
        }
    });
}


function deleteEntityCategoria() {
   
    let deleteUserUrl = '/categorias/' + userIdToDelete;

    $.ajax({
        url: deleteUserUrl,
        type: 'DELETE',
        success: function () {

            $("#deleta-card-id-" + userIdToDelete).remove();
           // let table = $("#card-lista");
            //table[0].remove();

            $('#alert-messages').append(
                "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"+
                "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"+
                "Categoria <strong>deletado</strong> com sucesso!"+
                "</div>"
            );
            closeModal('#delete-categoria-modal');
            userIdToDelete = "";
            rowIndexToDelete = "";
        }
    });
}

function deleteEntityEmpresa() {
   
    let deleteUserUrl = '/empresas/delete/' + userIdToDelete;

    $.ajax({
        url: deleteUserUrl,
        type: 'DELETE',
        success: function () {

            $("#deleta-card-id-" + userIdToDelete).remove();
            //let table = $("#tabela-empresas");
            //table[0].deleteRow(rowIndexToDelete);

            $('#alert-messages').append(
                "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"+
                "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"+
                "Empresa <strong>deletada</strong> com sucesso!"+
                "</div>"
            );
            closeModal('#delete-empresa-modal');
            userIdToDelete = "";
            rowIndexToDelete = "";
        },
        error: function(request,msg,error) {
            console.log('erro');
        }
    });
}

function deleteEntityCarro() {
   
    let deleteUserUrl = '/carros/delete/' + userIdToDelete;

    $.ajax({
        url: deleteUserUrl,
        type: 'DELETE',
        success: function () {

            $("#deleta-card-id-" + userIdToDelete).remove();
            //let table = $("#tabela-carros");
            //table[0].deleteRow(rowIndexToDelete);
            

            $('#alert-messages').append(
                "<div class='alert alert-danger alert-dismissible fade show' role='alert'>"+
                "<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>"+
                "Carro <strong>deletado</strong> com sucesso!"+
                "</div>"
            );
            closeModal('#delete-carro-modal');
            userIdToDelete = "";
            rowIndexToDelete = "";
        }
    });
}

function searchUserByProperty() {
    let selectedProperty = $("#search-user-dropdown option:selected").text();
    let value = $("#searchUserBar").val();

    if (value != null && value !== "") {
        window.location.href = "/adminPage/users?usersProperty=" + selectedProperty + "&propertyValue=" + value;
    }

    else {
        window.location.href = "/adminPage/users";
    }
}
