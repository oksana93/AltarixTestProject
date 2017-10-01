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

function deleteDepartment(id) {
    var department =
        {
            // id: $("#department-id-delete").val()
            id: id
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

function getInfo(id) {
    var department =
        {
            // id: $("#outputInfo").val()
            id: id
        };
    var request = {
            type: "POST",
            url: "/info/getInfoToDepartment",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            dataType: 'json',
            data: JSON.stringify(department)
        }
    ;
    $.ajax(request)
        .success(function (json) {
            alert("Department '" + json.departmentName +
                "' : , " + json.departmentDate +
                ". \n\nChief : " + json.chief +
                ". \n\nEmployees count : " + json.employeesCount +
                ". \n\nSalary(sum) : " + json.sumSalary +
                ". \n\nBranches (1 level) : " +json.branchesFirstLevel +
                ". \n\nBranches (all level) : " +json.branchesAllLevel +
                ". \n\nMasters (all level) : " +json.mastersAllLevel);
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}

function searchByName(name) {
    var department =
        {
            name: name
        };
    var request = {
            type: "POST",
            url: "/departments/searchByName",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            dataType: 'json',
            data: JSON.stringify(department)
        }
    ;
    $.ajax(request)
        .success(function (json) {
            alert(json.toString());
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}