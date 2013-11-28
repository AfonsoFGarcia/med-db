grammar PermissaoBuilder;

@header {
	import java.util.ArrayList;
	import pt.ist.sirs.permissoes.Permissao;
}

#token OR		"or"
#token AND		"and"
#token NOT		"not"
#token MDESP 	"mdesp"
#token MDP		"mdp"
#token MDU		"mdu"
#token MDEST	"mdest"
#token MDR		"mdr"
#token PDR		"pdr"
#token PPDE		"ppde"
#token PP		"pp"

b returns [Permissao value]
 :	s 				{$b.value = $s.value.get(0);}
 ;

s returns [ArrayList<Permissao> value]
@init {
	$value = new ArrayList<Permissao>();
}
 :	OR "(" s_1 ")"	{$s.value.add(new PermissaoOuLogico($s_1.value))}
 |	AND "(" s_1 ")" {$s.value.add(new PermissaoOuLogico($s_1.value))}
 |	c				{$s.value = $c.value;}
 ;

s_1 returns [ArrayList<Permissao> value]
 :	s 				{$s_1.value = $s.value;}
 ;

s_2 returns [ArrayList<Permissao> value]
 :	s 				{$s_2.value = $s.value;}
 ;

c returns [ArrayList<Permissao> value]
@init {
	$value = new ArrayList<Permissao>();
}
 :	s_1 "," s_2		{$c.value.addAll($s_1.value); $c.value.addAll($s_2.value);}
 |	NOT "(" t ")"	{$c.value.add($t.value);}
 |	t
 ;

t returns [Permissao value]
 :	
 |	MDESP			{$t.value = new PermissaoMedicoDaEspecialidade(null);}
 |	MDP				{$t.value = new PermissaoMedicoDaPessoa(null);}
 |	MDU				{$t.value = new PermissaoMedicoDeUrgencia(null);}
 |	MDEST			{$t.value = new PermissaoMedicoDoEstabelecimento(null);}
 |	MDR				{$t.value = new PermissaoMedicoDoRegisto(null);}
 |	PDR				{$t.value = new PermissaoPacienteDoRegisto(null);}
 |	PPDE			{$t.value = new PermissaoPoliticaDeEspecialidade(null);}
 |	PP				{$t.value = new PermissaoPublica(null);}
 ;

 INT
 :	"0"..."9"+
 ;