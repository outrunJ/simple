/**
 ** routers/common.js
 ** 通用接口路由
 **
 **/

var express  = require('express')
  , router   = express.Router()
  , user   = require('../../service/User.js');


router.post('/register', user.register);
router.post('/login', user.login);

module.exports = router;
