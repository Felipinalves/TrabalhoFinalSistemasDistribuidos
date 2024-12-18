import math
import time
from concurrent.futures import ProcessPoolExecutor

# Função para simular pontos
def simular_pontos(quantidade):
    import random  # Importação dentro da função
    dentro = 0
    for _ in range(quantidade):
        x = 2 * random.random() - 1  # Valor do eixo-x
        y = 2 * random.random() - 1  # Valor do eixo-y
        z = x**2 + y**2              # Distância ao quadrado
        if z <= 1:                   # Dentro do círculo
            dentro += 1
    return dentro

# Função principal
if __name__ == "__main__":
    total_iteracoes = 3000000  # Quantidade total de iterações
    n_processos = 8             # Número de processos paralelos

    iteracoes_por_processo = total_iteracoes // n_processos

    # Medir tempo de execução
    inicio = time.perf_counter()

    # Criar um pool de processos
    with ProcessPoolExecutor(max_workers=n_processos) as executor:
        resultados = list(executor.map(simular_pontos, [iteracoes_por_processo] * n_processos))

    # Somar os resultados parciais
    total_dentro = sum(resultados)
    total_fora = total_iteracoes - total_dentro

    # Calcular o valor de Pi
    sim_pi = 4 * total_dentro / total_iteracoes

    # Medir tempo final
    fim = time.perf_counter()
    tempo_execucao_ms = (fim - inicio) * 1000  # Convertendo para milissegundos

    # Imprimir os resultados
    print("Número de pontos dentro:", total_dentro)
    print("Número de pontos fora:", total_fora)
    print("Valor de Pi encontrado:", sim_pi)
    print(f"Tempo de execução: {tempo_execucao_ms:.2f} ms")
