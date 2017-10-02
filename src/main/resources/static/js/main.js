'use strict';

// ---------- DEPARTMENT ----------
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

function searchByName(/*name*/) {
    var department =
        {
            name: $("#department-name-search").val()
            // name: name
        };
    var request = {
            type: "POST",
            url: "/departments/getDepartmentByNamePart",
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

// ---------- EMPLOYEE ----------
function createEmployee() {
    var department =
        {
            id: $("#employee-department-create").val()
        };
    var job =
        {
            id: $("#employee-job-create").val()
        };
    var employee =
        {
            firstname: $("#employee-firstname-create").val(),
            lastname: $("#employee-lastname-create").val(),
            patronymic: $("#employee-patronymic-create").val(),
            gender: $("#employee-gender-create").val(),
            birthdate: $("#employee-birthdate-create").val(),
            department: department,
            hiredate: $("#employee-hiredate-create").val(),
            dismissal: $("#employee-dismissal-create").val(),
            job: job,
            salary: $("#employee-salary-create").val(),
            chief: ($("#employee-chief-create").is(":checked"))
        }
    var request = {
            type: "POST",
            url: "/employees/createEmployee",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(employee)
        }
    ;
    $.ajax(request)
        .done(function () {
            location.reload();
            alert("Employee created");
        })
        .fail(function (response) {
            location.reload();
            alert("Error: " + response.responseJSON.message);
        });
}

function deleteEmployee(id) {
    var employee =
        {
            id: id
        };
    var request = {
            type: "DELETE",
            url: "/employees/deleteEmployee",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(employee)
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
