/**
 *
 * Client side validation
 */
;
define(function (require, exports, module) {
    /**
     * @class Validation in client side
     * @constructor
     */
    function Validation() {
        this.errorMessage = {
            PASSWORD_NULL: 'Please enter password',
            PASSWORD_LENGTH: 'Password length must above 6',
            PASSWORD_AGAIN_NULL: 'Please confirm password again',
            PASSWORD_AGAIN_INVALID: 'Confirm password incorrect',
            CAPTCHA_NULL: 'Please enter validation',
            CAPTCHA_INVALID: 'Validation incorrect',
            MOBILE_NULL: 'Please enter phone number',
            MOBILE_INVALID: 'Please enter correct phone number',
            LOGINNAME_NULL: 'Please enter a username',
            LOGINNAME_EXISTS: 'Username already in use',
            LOGINNAME_INVALID: 'Length must in 2-30',
            NAME_NULL: 'Please enter your name',
            NAME_INVALID: 'Length must in 2-15',
            EMAIL_NULL: 'Please enter your email',
            EMAIL_INVALID: 'Please enter correct email',
            COMMENT_NULL: 'Please enter your comment'
        }
    }

    console.log('Validation model reference...');

    module.exports = new Validation();

    /**
     * check login name
     *
     * @method checkLoginName
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkLoginName = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'LOGINNAME_NULL');
        if ((/^[^\s]*$/.test(str) == true && /^(?!([1][3|5|8][0-9]{9}))[0-9a-zA-Z_]{2,30}$/.test(str)) == false) {
            return this.combineReturn(false, 'LOGINNAME_INVALID');
        }
        return this.combineReturn(true);
    }

    /**
     * check mobile
     *
     * @method checkMobile
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkMobile = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'MOBILE_NULL');
        if (!('' + str).match(/^[1][3|4|5|7|8][0-9]{9}$/)) return this.combineReturn(false, 'MOBILE_INVALID');
        return this.combineReturn(true);
    }

    /**
     * check email
     *
     * @method checkEmail
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkEmail = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'EMAIL_NULL');
        if (!('' + str).match(/[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+/)) return this.combineReturn(false, 'EMAIL_INVALID');
        return this.combineReturn(true);
    }

    /**
     * check password
     *
     * @method checkPassword
     * @param {String} str value
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.checkPassword = function (str) {
        if (!str || !str.length) return this.combineReturn(false, 'PASSWORD_NULL');
        if (str.length < 6) return this.combineReturn(false, 'PASSWORD_LENGTH');
        return this.combineReturn(true);
    }


    /**
     *  a validate route
     *
     * @method validate
     * @param {String} prop LoginName...
     * @param {String} value
     * @returns {Object}
     */
    Validation.prototype.validate = function (prop, value) {
        if (typeof(this["check" + prop]) != "function")
            return this.combineReturn(false);
        return this["check" + prop](value);
    }

    /**
     * combine return result
     *
     * @method combineReturn
     * @param {Boolean} success
     * @param {String} error ErrorMessage Code
     * @returns {{success: (true|false), comment: string}}
     */
    Validation.prototype.combineReturn = function (success, error) {
        var obj = {success: success};
        if (error) obj.comment = this.errorMessage[error] || null;
        return obj;
    }
});