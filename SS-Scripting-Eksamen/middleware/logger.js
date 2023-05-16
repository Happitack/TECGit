const { extname } = require("path");

const logger = (option) => {
    return (req, res, next) => {
        if (option.ignore.includes(extname(req.path))) {
            next();
            return;            
        }
        if (option.off) {
            next();
            return;
        }
        let logStr = new Date().toLocaleTimeString("da-DK");
        logStr += ` ${req.method} ${req.path}`;
        res.on("finish", () => {
            logStr += ` ${res.statusCode} ${res.statusMessage}`;
            console.log(logStr);
        })
        next();
    }
}

module.exports = logger;

