<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:body>
        <body ng-app="myApp" class="ng-cloak">
        <div class="generic-container" ng-controller="UserController as ctrl">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="lead">User Registration Form </span></div>
                <div class="formcontainer">
                    <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                        <input type="hidden" ng-model="ctrl.user.id" />
                        <input type="hidden" ng-model="ctrl.user.createDate" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="uname">Name</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="ctrl.user.name" id="uname" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                                    <div class="has-error" ng-show="myForm.$dirty">
                                        <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                        <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="age">Age</label>
                                <div class="col-md-7">
                                    <input type="number" ng-model="ctrl.user.age" id="age" class="form-control input-sm" placeholder="Enter your Age."/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="admin">Admin</label>
                                <div class="col-md-7">
                                    <input type="checkbox" ng-model="ctrl.user.admin" id="admin" class="checkbox input-sm"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
                                <input type="submit"  value="{{!ctrl.user.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                                <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading"><span class="lead">Find Users from Name</span></div>
                <div class="formcontainer">
                    <form name="myForm" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-7">
                                <input type="text" ng-model="f.name" id="find" class="form-control input-sm" placeholder="Enter name"/>
                            </div>
                            <div class="col-md-3">
                                <button type="button" ng-click="ctrl.find(f.name)" class="btn btn-success custom-width">Find</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading"><span class="lead">List of Users </span></div>
                <div class="tablecontainer">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>ID.</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Admin</th>
                            <th>Create Date</th>
                            <th width="20%"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="u in ctrl.users">
                            <td><span ng-bind="u.id"></span></td>
                            <td><span ng-bind="u.name"></span></td>
                            <td><span ng-bind="u.age"></span></td>
                            <td><span ng-bind="u.admin"></span></td>
                            <td><span ng-bind="u.createDate"></span></td>
                            <td>
                                <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </jsp:body>
</page:template>

