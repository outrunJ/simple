/**
 * Created by outrun on 6/25/17.
 */
var
  mongodb = require('../lib/mongo'),
  Schema = mongodb.Schema;


var userSchema = new Schema({
  type: Number, // 1 admin
  name: String,
  pwd: String,
}, {
  collection: 'users'
});

var User = mongodb.model('User', userSchema);


module.exports = User;