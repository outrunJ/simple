/**
 * Created by outrun on 6/25/17.
 */

var tool = require('../tool/common'),
  User = require('../model/User')

exports.register = function (req, res) {
  var name = req.body.name
  var pwd = req.body.pwd
  var requser = {name: name, pwd: pwd}
  User.findOne(requser).then(function (user) {
    if (user != undefined) {
      throw "user found"
    }
  }).then(function () {
    User.create(requser)
  }).then(function () {
    res.status(200).json({
      token: tool.token(name, pwd)
    })
  }, function (err) {
    res.status(200).json({
      code: '0001',
      msg: err.message,
    })
  })

}
exports.login = function (req, res) {
  var name = req.body.name
  var pwd = req.body.pwd
  var requser = {name: name, pwd: pwd}

  User.findOne(requser).then(function (user) {
    if (user == undefined) {
      throw "user not found"
    }
  }).then(function () {
    res.status(200).json({
      token: tool.token(name, pwd)
    })
  }, function (err) {
    res.status(200).json({
      code: '0001',
      msg: err.message,
    })
  })

}