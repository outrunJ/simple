/**
 * Created by outrun on 6/25/17.
 */
/**
 *  db
 *  文档数据库基本配置
 *  Created by Jacky.L on 4/16/14.
 *  Copyright (c) 2014 ZLYCare. All rights reserved.
 */
var mongoose = require('mongoose');

mongoose.connect("mongodb://localhost/yzc");
var connection = mongoose.connection;

connection.on('error', function (err) {
  console.log('Connection to mongodb error: ' + err);
});

connection.once('open', function () {
  console.log('Connect to mongodb sucessfully ');
});

mongoose.set('debug', true);

module.exports = mongoose;
