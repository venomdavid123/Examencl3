package com.bd.productos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bd.productos.modelo.ClassProducto;
import com.bd.productos.servicios.IProductoServicio;

@Controller
@RequestMapping("/ControlProducto")
public class ProductoController {
	//aplicamos una inyeccion de dependencia
	@Autowired
	private IProductoServicio iproductoservicio;
	//creamos un metodo para listar producto
	@GetMapping("/ListadoProductos")
	public String ListadoProducto(Model modelo) {
		List<ClassProducto> listar=iproductoservicio.ListadoProductos();		
		//enviamos datos a la vista
		modelo.addAttribute("listadoproductos" ,listar); //poner en la vista
		//retornar a la vista
		return "Vistas/Producto/ListadoProducto";
	}//fin	metodo listado producto
	
	//creamos el metodo registrar...
	
	@GetMapping("/RegisProducto")
	public String RegistrarProducto(Model model) {
		//realizamos la respectiva instancia...
		ClassProducto clapro=new ClassProducto();
		//enviamo el objeto hacia la vista...
		model.addAttribute("regpro",clapro);
		//retornar a la vista
		return "Vistas/Producto/FrmRegisProducto";
	}
	
	@PostMapping("/GuardarProducto")
	public String GuardarProducto(@ModelAttribute ClassProducto clapro) {
		//aplicamos una inyeccion de dependencia
		//e invocamos el metodo registrar producto
		iproductoservicio.RegistrarProducto(clapro);	
		//retornamos al listado
		return "redirect:/ControlProducto/ListadoProductos";
	}  //fin del metodo guardar 
	
	//********creamos el metodo editar..
		@GetMapping("/Editar/{id}")
		public String Editar(@PathVariable("id") Integer idproductocl3,Model modelo) {
			//buscamos el codigo para asignarlo en el formulario
			ClassProducto buspro=iproductoservicio.BuscarPorId(idproductocl3);
			//enviamos a la vista
			modelo.addAttribute("regpro",buspro);
			//retornar  a la vista(formulario registrar )
			return "Vistas/Producto/FrmRegisProducto";
				
		}  //fin del metodo editar..
		//********creamos el metodo Eliminar..
		@GetMapping("/Eliminar/{id}")
		public String Eliminar(@PathVariable("id") Integer idproductocl3,Model modelo) {
			//aplicamos inyeccion de dependencia..
			iproductoservicio.EliminarProducto(idproductocl3);
			List<ClassProducto> listado=iproductoservicio.ListadoProductos();
			//enviamos a la vista...
			modelo.addAttribute("listadoproductos",listado);
			//retornamos al listado
			return "redirect:/ControlProducto/ListadoProductos";
			
		}  //fin del metodo eliminar...*/
	
}