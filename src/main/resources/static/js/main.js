'use strict';

function createDepartment() {
    var departmentMaster =
        {
            id: $("#departmentMaster-id-create").val()
        }
    var department =
        {
            name: $("#department-name-create").val(),
            date: $("#department-date-create").val(),
            departmentMaster: departmentMaster
        };
    var request = {
            type: "POST",
            url: "/departments/createUniqueDepartment",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(department)
        }
    ;
    $.ajax(request)
        .done(function () {
            location.reload();
            alert("Department created");
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}

function updateDepartment() {
    var idMaster = $("#departmentMaster-id-update").val();
    var newName = $("#department-name-update").val();
    var id = $("#department-id-update").val();
    var departmentMaster =
        {
            id: idMaster
        }
    var department =
        {
            name: newName,
            id: id,
            departmentMaster: departmentMaster
        };
    if (newName != null && newName != "") {
        var request = {
                type: "POST",
                url: "/departments/updateUniqueDepartmentName",
                headers: {
                    'Accept': 'application/json;charset=UTF-8',
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                data: JSON.stringify(department)
            }
        ;
        $.ajax(request)
            .done(function () {
                alert("Department updated");
            })
            .fail(function (response) {
                alert("Error: " + response.responseJSON.message);
            });
    }
    if (idMaster != null && idMaster != "") {
        var request = {
                type: "POST",
                url: "/departments/updateDepartmentMaster",
                headers: {
                    'Accept': 'application/json;charset=UTF-8',
                    'Content-Type': 'application/json;charset=UTF-8'
                },
                data: JSON.stringify(department)
            }
        ;
        $.ajax(request)
            .done(function () {
                alert("Department updated");
            })
            .fail(function (response) {
                alert("Error: " + response.responseJSON.message);
            });
    }
    location.reload();
}

function deleteDepartment() {
    var department =
        {
            id: $("#department-id-delete").val()
        };
    var request = {
            type: "DELETE",
            url: "/departments/deleteDepartment",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(department)
        }
    ;
    $.ajax(request)
        .done(function () {
            location.reload();
            alert("Department deleted");
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}

function getInfo() {
    var department =
        {
            name: $('#outputInfo').val()
        };
    var request = {
            type: "GET",
            url: "/departments/getInfoToDepartment",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(department)
        }
    ;
    $.ajax(request)
        .done(function () {
            location.reload();
            alert("Department info - success");
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}
// function getInfo() {
//     var output = $('#outputDepartmentInfo');
//     var departmentName = $('#outputInfo').val();
//     var request = {
//         type: "GET",
//         url: "/departments/getInfoToDepartment",
//         headers: {
//             'Accept': 'application/json;charset=UTF-8',
//             'Content-Type': 'application/json;charset=UTF-8'
//         },
//         // dataType: 'json',
//         data: JSON.stringify({
//             name: departmentName
//         })
//     };
//     $.ajax(request)
//         .error(function () {
//             alert("Error: " + response.responseJSON.message);
//         })
//         .done(function (/*json*/) {
//             // output.html(json);
//             alert("Department deleted");
//         })
// }