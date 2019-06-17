/**
 * Created by outrun on 6/25/17.
 */

var crypto = require('crypto')

var MD5 = function (data, salt, upper) {
  if (data && salt) data += salt;
  if (upper)
    return crypto.createHash('md5').update(data).digest('hex').toUpperCase();
  else
    return crypto.createHash('md5').update(data).digest('hex');
};

module.exports.token = function (name, pwd) {
  return MD5(name+pwd, 'yzc', true)
}
