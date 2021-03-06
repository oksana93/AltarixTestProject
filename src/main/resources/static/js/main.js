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
            alert("Error: " + response.message);
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
                alert("Error: " + response.message);
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
            alert("Error: " + response.message);
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
                "' : " + json.departmentDate +
                "\n\nChief : " + json.chief +
                "\n\nEmployees count : " + json.employeesCount +
                "\n\nSalary(sum) : " + json.sumSalary +
                "\n\nBranches (1 level) : " + json.branchesFirstLevel +
                "\n\nBranches (all level) : " + json.branchesAllLevel +
                "\n\nMasters (all level) : " + json.mastersAllLevel);
        })
        .fail(function (response) {
            alert("Error: " + response.message);
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
            alert("Error: " + response.message);
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
            alert("Error: " + response.message);
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
            alert("Employee deleted");
        })
        .fail(function (response) {
            alert("Error: " + response.message);
        });
}

function dismissalEmployee(id) {
    var current_date = new Date();
    var dismissalStr = prompt('Dismissal date', current_date.getFullYear() + "-" + current_date.getMonth() + "-" + current_date.getDate());
    if (dismissalStr == null) return;
    var dismissal = new Date(dismissalStr);
    var employee =
        {
            id: id,
            dismissal: dismissal
        };
    var request = {
            type: "POST",
            url: "/employees/dismissalEmployee",
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
            alert("Employee are dismissed");
        })
        .fail(function (response) {
            alert("Error: " + response.message);
        });
}

function transferEmployee() {
    var department = {
        id : $("#department-id-transfer").val()
    }
    var employee =
        {
            id : $("#employee-id-transfer").val(),
            department: department

        };
    var request = {
            type: "POST",
            url: "/employees/transferEmployee",
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
            alert("A successful relocation");
        })
        .fail(function (response) {
            alert("Error: " + response.message);
        });
}

function transferEmployees() {
    var department = {
        id : $("#department-id-transfer-employees").val()
    }
    var request = {
            type: "POST",
            url: "/employees/transferEmployees",
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
            alert("A successful relocation (all employees)");
        })
        .fail(function (response) {
            alert("Error: " + response.message);
        });
}

function chiefByEmployeeId(id) {
    var employee = {
        id : id
    }
    var request = {
            type: "POST",
            url: "/employees/chiefByEmployeeId",
            headers: {
                'Accept': 'application/json;charset=UTF-8',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            data: JSON.stringify(employee)
        }
    ;
    $.ajax(request)
        .success(function (json) {
            alert("Department '" + json.departmentName +
                "\n\nChief : " + json.chief);
        })
        .fail(function (response) {
            alert("Error: " + response.message);
        });
}
