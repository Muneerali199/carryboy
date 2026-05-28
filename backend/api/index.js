module.exports = (req, res) => {
  res.status(200).json({ 
    message: "Welcome to the CarryBoy API!",
    status: "Online",
    version: "1.0.0"
  });
};
