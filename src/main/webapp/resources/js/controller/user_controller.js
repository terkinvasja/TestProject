'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null, name:'', age:'', createDate:'', admin: false};
    self.users=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.find = find;


    listUsers();

    function listUsers(){
        UserService.listUsers()
            .then(
            function(d) {
                self.users = d;

                self.users.forEach(function(item, i, users) {
                    console.info("Старая дата:" + item.createDate);
                    var date = new Date(item.createDate);
                    var curr_date = date.getDate();
                    var curr_month = date.getMonth() + 1;
                    var curr_year = date.getFullYear();
                    if (curr_month < 10) curr_month = "0" + curr_month;
                    if (curr_date < 10) curr_date = "0" + curr_date;
                    item.createDate = curr_year + "-" + curr_month + "-" + curr_date;
                    console.info("Новая дата:" + item.createDate);
                });
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    function addUser(user){
        UserService.addUser(user)
            .then(
            listUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            listUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }

    function removeUser(id){
        UserService.removeUser(id)
            .then(
            listUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }

    function findUsers(name){
        UserService.findUsers(name)
            .then(
                function(d) {
                    self.users = d;

                    self.users.forEach(function(item, i, users) {
                        console.info("Старая дата:" + item.createDate);
                        var date = new Date(item.createDate);
                        var curr_date = date.getDate();
                        var curr_month = date.getMonth() + 1;
                        var curr_year = date.getFullYear();
                        if (curr_month < 10) curr_month = "0" + curr_month;
                        if (curr_date < 10) curr_date = "0" + curr_date;
                        item.createDate = curr_year + "-" + curr_month + "-" + curr_date;
                        console.info("Новая дата:" + item.createDate);
                    });
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            addUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        removeUser(id);
    }


    function reset(){
        self.user={id:null, username:'', age:'', isAdmin: false, createdDate:''};
        $scope.myForm.$setPristine(); //reset Form
    }

    function find(name) {
        if (name != null) {
            findUsers(name);
            console.log('Finds ', name);
        } else {
            listUsers();
        }

    }

}]);
