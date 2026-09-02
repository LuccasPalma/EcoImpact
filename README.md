# EcoImpact 🌱

O EcoImpact é um aplicativo Android desenvolvido para fins acadêmicos com o objetivo de conscientizar sobre o impacto ambiental e ESG (Environmental, Social, and Governance).

## Funcionalidades
- **Monitoramento da Qualidade do Ar:** Consulta dados reais em tempo real através da API Open-Meteo para diversas cidades brasileiras.
- **Calculadora de Emissões de CO₂:** Estima a emissão de dióxido de carbono com base no meio de transporte e distância percorrida.
- **Dicas Sustentáveis:** Informações e sugestões práticas para reduzir a pegada de carbono.

## Tecnologias Utilizadas
- **Kotlin:** Linguagem de programação principal.
- **Jetpack Compose:** Framework moderno para construção de interfaces nativas.
- **Navigation Compose:** Gerenciamento de navegação entre telas.
- **Kotlinx Serialization:** Parsing eficiente de dados JSON.
- **Coroutines:** Operações assíncronas para chamadas de API.
- **Open-Meteo Air Quality API:** Fonte de dados reais sobre poluentes atmosféricos.

## Estrutura do Projeto
- `MainActivity.kt`: Ponto de entrada e configuração da navegação.
- `AirQualityApi.kt`: Cliente de API para consulta de qualidade do ar.
- `model/`: Modelos de dados para Cidades, Qualidade do Ar e Transporte.
- `ui/theme/`: Definições de cores (identidade verde), tipografia e formas.
- `Tela*.kt`: Implementação das 5 telas principais do fluxo.

## Como Executar
1. Clone o repositório.
2. Abra no Android Studio.
3. Sincronize o Gradle.
4. Execute em um emulador (recomendado Pixel 7) ou dispositivo físico.

## Metodologia da Calculadora
As estimativas de emissão são baseadas em fatores médios de mercado:
- **Carro (Gasolina):** 0.18 kg CO₂/km
- **Moto:** 0.09 kg CO₂/km
- **Ônibus:** 0.03 kg CO₂/km
- **Bicicleta/Caminhada:** 0.00 kg CO₂/km

## Créditos
Dados de qualidade do ar fornecidos por [Open-Meteo](https://open-meteo.com/).
Este é um projeto acadêmico. As medições são baseadas em modelos de previsão e estimativas.
