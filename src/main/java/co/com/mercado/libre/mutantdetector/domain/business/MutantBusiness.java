package co.com.mercado.libre.mutantdetector.domain.business;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class MutantBusiness {

    public Mono<Boolean> isMutant(List<String> dna) {
        List<String> dnaReverseOrder = dna
                .stream()
                .map(dnaRow -> new StringBuilder(dnaRow)
                        .reverse()
                        .toString())
                .collect(Collectors.toList());
        List<Boolean> hasMutantDna = new ArrayList<>();
        hasMutantDna.add(matrixHasMutantLettersInX(dna));
        hasMutantDna.add(matrixHasMutantLettersInY(dna));
        hasMutantDna.add(matrixHasMutantLettersInObliqueAxis(dna));
        hasMutantDna.add(matrixHasMutantLettersInObliqueAxis(dnaReverseOrder));
        return Mono.justOrEmpty(hasMutantDna.stream().anyMatch(Boolean::booleanValue));
    }

    private boolean matrixHasMutantLettersInY(List<String> dna) {
        AtomicInteger index = new AtomicInteger(0);
        boolean hasMutantDna = false;
        while (index.get() < dna.size()) {
            if (hasMutantDna(transposeMatrix(index.getAndIncrement(), dna))) {
                hasMutantDna = true;
                break;
            }
        }
        return hasMutantDna;
    }

    private boolean matrixHasMutantLettersInObliqueAxis(List<String> dna) {
        boolean hasMutantDna = false;
        int initialIndex = dna.size() - 4;
        AtomicInteger index = new AtomicInteger(initialIndex);
        while (Math.abs(index.get()) <= initialIndex) {
            if (hasMutantDna(transposeMatrixOblique(index.getAndDecrement(), dna))) {
                hasMutantDna = true;
                break;
            }
        }
        return hasMutantDna;
    }

    private String transposeMatrixOblique(int positionalIndex, List<String> dna) {
        AtomicInteger index;
        String obliqueRow = "";
        if (positionalIndex < 0) {
            index = new AtomicInteger(Math.abs(positionalIndex));
            StringBuilder stringBuilder = new StringBuilder();
            while (dna.size() > index.get()) {
                stringBuilder.append(getObliqueRow(dna.get(index.getAndIncrement()), new AtomicInteger(0)));
            }
            obliqueRow = stringBuilder.toString();
        } else {
            index = new AtomicInteger(positionalIndex);
            obliqueRow = dna.stream().map(dnaRow -> getObliqueRow(dnaRow, index)).collect(Collectors.joining());
        }
        return obliqueRow;
    }

    private String getObliqueRow(String dnaRow, AtomicInteger index) {
        String letter = "";
        if (index.get() < dnaRow.length()) {
            letter = String.valueOf(dnaRow.charAt(index.getAndIncrement()));
        }
        return letter;
    }

    private boolean matrixHasMutantLettersInX(List<String> dna) {
        return dna.stream().anyMatch(this::hasMutantDna);
    }

    private String transposeMatrix(int index, List<String> dna) {
        return dna.stream().map(dnaRow -> String.valueOf(dnaRow.charAt(index))).collect(Collectors.joining());
    }

    private boolean hasMutantDna(String dnaRow) {
        return dnaRow.contains("AAAA") || dnaRow.contains("GGGG") || dnaRow.contains("CCCC") || dnaRow.contains("TTTT");
    }

}
