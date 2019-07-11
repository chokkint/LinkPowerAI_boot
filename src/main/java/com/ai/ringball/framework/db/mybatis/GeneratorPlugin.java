package com.ai.ringball.framework.db.mybatis;

import com.ai.ringball.framework.constants.SysConstants;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class GeneratorPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		FullyQualifiedJavaType listType = introspectedTable.getRules().calculateAllFieldsClass();

		importedTypes.add(listType);
		returnType.addTypeArgument(listType);
		method.setReturnType(returnType);

		importedTypes.add(returnType);

		method.setName("selectAllByPage");

		FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewMapInstance();

		parameterType.addTypeArgument(FullyQualifiedJavaType.getStringInstance());
		parameterType.addTypeArgument(FullyQualifiedJavaType.getObjectInstance());

		Parameter parameter = new Parameter(parameterType, "map");

		importedTypes.add(parameterType);

		method.addParameter(parameter);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);

		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

		XmlElement parentElement = document.getRootElement();

		parentElement.addElement(new TextElement(SysConstants.CONSTANT_NULL_STRING));

		context.getCommentGenerator().addComment(parentElement);
		XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id", "selectAllByPage")); //$NON-NLS-1$
		answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));//$NON-NLS-1$

		String parameterType = "map";

		answer.addAttribute(new Attribute("parameterType", parameterType));//$NON-NLS-1$

		StringBuilder sb = new StringBuilder();
		sb.append("select "); //$NON-NLS-1$

		if (introspectedTable.hasBaseColumns()) {
			answer.addElement(new TextElement(sb.toString()));
			answer.addElement(getBaseColumnListElement(introspectedTable));
		} else {

			Iterator<IntrospectedColumn> iter = introspectedTable.getAllColumns().iterator();
			while (iter.hasNext()) {
				sb.append(MyBatis3FormattingUtilities.getSelectListPhrase(iter.next()));

				if (iter.hasNext()) {
					sb.append(", "); //$NON-NLS-1$
				}

				if (sb.length() > 80) {
					answer.addElement(new TextElement(sb.toString()));
					sb.setLength(0);
				}
			}
			answer.addElement(new TextElement(sb.toString()));
		}

		sb.setLength(0);
		sb.append("from "); //$NON-NLS-1$
		sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		parentElement.addElement(answer);

		document.setRootElement(parentElement);

		return true;
	}

	protected XmlElement getBaseColumnListElement(IntrospectedTable introspectedTable) {
		XmlElement answer = new XmlElement("include"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("refid", introspectedTable.getBaseColumnListId()));//$NON-NLS-1$
		return answer;
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		System.out.println("sqlMapInsertElementGeneratedtest");
		List<IntrospectedColumn> IntrospectedColumnList = introspectedTable.getPrimaryKeyColumns();
		if (IntrospectedColumnList.size() == 1) {
			IntrospectedColumn introspectedColumn = IntrospectedColumnList.get(0);
			XmlElement answer = new XmlElement("selectKey");
			answer.addAttribute(new Attribute("order", "BEFORE"));
			answer.addAttribute(new Attribute("resultType", "string"));
			answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
			answer.addElement(new TextElement("select sys_guid() from dual"));
			element.addElement(0, answer);
		}
		return true;
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		System.out.println("sqlMapInsertSelectiveElementGeneratedtest");
		List<IntrospectedColumn> IntrospectedColumnList = introspectedTable.getPrimaryKeyColumns();
		if (IntrospectedColumnList.size() == 1) {
			IntrospectedColumn introspectedColumn = IntrospectedColumnList.get(0);
			XmlElement answer = new XmlElement("selectKey");
			answer.addAttribute(new Attribute("order", "BEFORE"));
			answer.addAttribute(new Attribute("resultType", "string"));
			answer.addAttribute(new Attribute("keyProperty", introspectedColumn.getJavaProperty()));
			answer.addElement(new TextElement("select sys_guid() from dual"));
			element.addElement(0, answer);
		}
		return true;
	}
}
