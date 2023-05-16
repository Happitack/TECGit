const express = require("express");
const PORT = process.env.PORT || 5000;
const NODE_ENV = process.env.NODE_ENV || "production";
const logger = require("./middleware/logger");


const app = express();

app.set("view engine", "pug");

app.use(express.urlencoded({ extended: true }));

app.use(logger({off: false, ignore: [".js", ".css"]}));

app.get("/", (req, res) => {
    res.render("home");
});

app.use(express.static("public"));

app.get("/about", (req, res) => {
    res.render("about");
});

app.use("/subscriber", require("./routes/subscriber"));

app.listen(PORT, () => {
    let msg = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
    msg += new Date().toLocaleTimeString("da-DK");
    msg += ` Server started in ${NODE_ENV} mode. \nGo to http://localhost:${PORT}`;
    msg += "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    console.log(msg);
});