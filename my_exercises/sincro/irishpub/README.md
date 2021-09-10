# desc
+ In un irish pub i clienti che portano uno strumento musicale e suonano ricevono delle birre in omaggio dal proprietario ogni volta che fanno una pausa (smettendo di suonare).

+ Il proprietario offre M birre e non di più

+ Ad ogni pausa, il cliente musicista aspetta di vedere se il proprietario gli porta la birra
	+ Se si, beve e poi ricomincia a suonare
	+ Se no, dopo un certo tempo ricomincia a suonare

+ Il cliente musicista suona un certno numero di volte (diverso per ogni musicista) e poi va a csa

+ Il proprietario è sempre in attesa di vedere se qualche musicista ha sete (finché non finisce le birre gratis).

> Sincronizzare il thread Holder e i thread Musician sull'evento: un cliente musicista ha smesso di suonare e ha voglia di birra.

