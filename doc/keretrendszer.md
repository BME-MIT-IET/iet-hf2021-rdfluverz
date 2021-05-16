# Build keretrendszer beüzemelése 

Az első gondolat egy Maven keretrendszer beüzemelése volt, de mivel az eredeti projekt 7 évvel ezelőtti, sok Maven dependency azóta megújult,
máshova került át. 20 commit alatt sem sikerült megoldani a problémákat (`actions-setup branch`), ezért új módszerhez folyamodtam.

Új branch-en kezdtem el dolgozni (`ant-actions`) 

Mivel a projekt eredeti leírása szerint Ant build-el dolgozik, ezért kikerestem, hogy a GitHub-nak van-e Ant-hez Workflow template-je. 
Szerencsére volt, így annak a dokumentáció szerintibeüzemelésével sikeresen lefutott az Actions.
