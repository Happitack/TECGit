const express = require("express");
const axios = require("axios");
const { getAll, update, insert } = require("../dataservice/subscriber-service");

const router = express.Router();


router.get("/add", (req, res) => {
    res.render("add-subscriber");
});

function isValidInput(input) {
    
    const rx = /^\w+@\w+\.\w{2,5}/
    for (const [key, value] of Object.entries(input)) {
        if (key === "email") {
            const rxRes = rx.test(value);
            if (!rxRes) {
                return { error: true, msg: `Fejl i ${key}` }
            }
        }
        if (value.length < 2) {
            // return false;
            return { error: true, msg: `Fejl i ${key}` }
        }
    }
    return {error: null}
}

router.post("/add", async (req, res) => {

    const validation = isValidInput(req.body);
    if(validation.error) {
        res.render("error", { err: validation.msg });
        return;
    }

    const data = await insert(req, res);
    if (data.err) {
        console.log(data.err);
        res.render("error", { err: data.err });
    }
    else {
        res.redirect("/subscriber");
    }
})

router.get("/edit/:id", async (req, res) => {
    const id = req.params.id;
    // if(!Number.isInteger(123.456))
    if (isNaN(id)) {
        res.render("error", { err: "Paremeter id er ikke et tal" });
        return;
    }
    const result = await axios.get("http://localhost:3000/test/" + id);
    res.render("edit-subscriber", { data: result.data });
});

router.post("/edit/:id", async (req, res) => {
    const data = await update(req, res);
    res.redirect("/subscriber");
})

router.get("/delete/:id", async (req, res) => {
    const id = req.params.id
    const result = await axios.delete("http://localhost:3000/test/" + id)
    res.redirect("/subscriber");
})

router.get("/", async (req, res) => {
    const data = await getAll(req, res);
    res.render("subscriber", {data});
});

module.exports = router;