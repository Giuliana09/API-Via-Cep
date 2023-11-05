document.getElementById('cep').addEventListener('blur', function () {
    const cep = this.value;
    if (cep) {
        fetch(`https://viacep.com.br/ws/${cep}/json/`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('logradouro').value = data.logradouro;
                document.getElementById('complemento').value = data.complemento;
                document.getElementById('bairro').value = data.bairro;
                document.getElementById('localidade').value = data.localidade;
                document.getElementById('uf').value = data.uf;
                document.getElementById('ibge').value = data.ibge;
                document.getElementById('gia').value = data.gia;
                document.getElementById('ddd').value = data.ddd;
                document.getElementById('siafi').value = data.siafi;
            })
            .catch(error => {
                console.error(error);
            });
    }
});