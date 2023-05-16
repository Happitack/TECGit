const axios = require("axios");

const DBURL = "http://localhost:3000/test";

const getAll = async (req, res) => {
    try {
        const result = await axios.get(DBURL);
        return result.data;
    } catch (error) {
        console.log(`Axios: ${error.response.status}`);
    }
}

const update = async (req, res) => {
    try {
        const result = await axios.put(`${DBURL}/${req.params.id}`, req.body);
        console.log(result.data);
        return result.data;
    } catch(error) {
        console.log(error.message);
    }
}

const insert = async (req, res) => {
    try {
        const result = await axios.post(DBURL, req.body);
        return result.data;
    }
    catch (err) {
        console.log(`Axios: ${err.response.status}`);
        return { err: `Axios: ${err.response.status}` };
    }
}

module.exports = { getAll, update, insert };