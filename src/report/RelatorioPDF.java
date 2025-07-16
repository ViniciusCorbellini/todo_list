package report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dao.DesafioDAO;
import dao.TarefaDAO;
import model.Desafio;
import model.Tarefa;

import java.io.FileOutputStream;
import java.util.List;

public class RelatorioPDF {

    public static void gerarRelatorioDesafios(String nomeArquivo, long dono_id) throws Exception {
        DesafioDAO desafioDAO = new DesafioDAO();
        List<Desafio> desafios = desafioDAO.findAll(dono_id); // método real

        if (desafios.isEmpty()) {
            throw new Exception("Nenhum desafio encontrado.");
        }

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(nomeArquivo));
        doc.open();

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        doc.add(new Paragraph("Relatório de Desafios\n\n", tituloFont));

        for (Desafio d : desafios) {
            String texto = String.format(
                "ID: %d\nNome: %s\nDescrição: %s\nCompleto: %s\nData de Início: %s\nData de Fim: %s\n\n",
                d.getId(), d.getNome(), d.getDescricao(), d.getCompleto(), d.getDataInicio(), d.getDataFim()
            );
            doc.add(new Paragraph(texto, textoFont));
        }

        doc.close();
    }

    public static void gerarRelatorioTarefas(String nomeArquivo, long dono_id) throws Exception {
        TarefaDAO tarefaDAO = new TarefaDAO();
        List<Tarefa> tarefas = tarefaDAO.findAllFromUser(dono_id); 

        if (tarefas.isEmpty()) {
            throw new Exception("Nenhuma tarefa encontrada.");
        }

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(nomeArquivo));
        doc.open();

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        doc.add(new Paragraph("Relatório de Tarefas\n\n", tituloFont));

        for (Tarefa t : tarefas) {
            String grupo = (t.getGrupo() != null) ? t.getGrupo().getNome() : "Sem grupo";
            String texto = String.format(
                "ID: %d\nTítulo: %s\nDescrição: %s\nGrupo: %s\n\n",
                t.getId(), t.getTitulo(), t.getDescricao(), grupo
            );
            doc.add(new Paragraph(texto, textoFont));
        }

        doc.close();
    }
}
