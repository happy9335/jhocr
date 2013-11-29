/**
 * TODO: describe: <one line to give the program's name and a brief idea of what it does.>
 * 
 * <br>
 * Copyright (©) 2013 Pablo Filetti Moreira
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.googlecode.jhocr.element;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.jhocr.attribute.BBox;

public class HocrPage extends HocrElement {

	public static final String	TAGNAME		= "div";
	public static final String	CLASSNAME	= "ocr_page";

	private HocrDocument		document;
	private String				image;
	private List<HocrCarea>		careas		= new ArrayList<HocrCarea>();

	public HocrPage(String id, BBox bbox, String image) {
		super(id, bbox);
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<HocrCarea> getCareas() {
		return careas;
	}

	public void setCareas(List<HocrCarea> careas) {
		this.careas = careas;
	}

	public void addCarea(HocrCarea carea) {
		carea.setPage(this);
		getCareas().add(carea);
	}

	@Override
	public String getClassName() {
		return CLASSNAME;
	}

	@Override
	public String getTagName() {
		return TAGNAME;
	}

	public List<HocrParagraph> getParagraphs() {

		List<HocrParagraph> paragraphs = new ArrayList<HocrParagraph>();

		for (HocrCarea carea : getCareas()) {
			paragraphs.addAll(carea.getParagraphs());
		}

		return paragraphs;
	}

	public List<HocrLine> getLines() {

		List<HocrLine> lines = new ArrayList<HocrLine>();

		for (HocrParagraph paragraph : getParagraphs()) {
			lines.addAll(paragraph.getLines());
		}

		return lines;
	}

	public List<HocrWord> getWords() {

		List<HocrWord> words = new ArrayList<HocrWord>();

		for (HocrLine line : getLines()) {
			words.addAll(line.getWords());
		}

		return words;
	}

	public void setDocument(HocrDocument document) {
		this.document = document;
	}

	public HocrDocument getDocument() {
		return document;
	}

	@Override
	public String toString() {
		return "HocrPage{" + super.toString() + ", image=" + image + ", careas=" + careas.size() + '}';
	}
}