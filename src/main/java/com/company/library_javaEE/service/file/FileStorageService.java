package com.company.library_javaEE.service.file;

import com.company.library_javaEE.configs.ApplicationContextHolder;
import com.company.library_javaEE.dao.AbstractDAO;
import com.company.library_javaEE.dao.UploadDAO;
import com.company.library_javaEE.domains.Uploads;
import com.company.library_javaEE.dto.UploadsDTO;
import com.company.library_javaEE.exceptions.NotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 11:52 PM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class FileStorageService extends AbstractDAO<UploadDAO> {

    private static FileStorageService instance;

    private Path rootPath = Paths.get ( "/home/dilshodbek/Uploads/uploads" );


    public static FileStorageService getInstance() {
        if (instance == null) {
            instance = new FileStorageService ();
        }
        return instance;
    }

    private FileStorageService() {
        super ( ApplicationContextHolder.getBean ( UploadDAO.class ) );
    }


    public UploadsDTO getByPath(String path) throws NotFoundException {
        Optional<Uploads> byPath = dao.getByPath ( path );

        if (byPath.isEmpty ()) {
            throw new NotFoundException ( "File not found " );
        }

        Uploads uploads = byPath.get ();
        return UploadsDTO.builder ()
                .id ( uploads.getId () )
                .path ( uploads.getPath () )
                .generatedName ( uploads.getGeneratedName () )
                .size ( uploads.getSize () )
                .contentType ( uploads.getContentType () )
                .originalName ( uploads.getOriginalName () )
                .build ();
    }

    public Uploads uploads(Part part) {
        try {
            String contentType = part.getContentType ();
            String originalFileName = part.getSubmittedFileName ();
            originalFileName = originalFileName.replaceAll ( ",", "_" );
            long size = part.getSize ();
            String[] split = originalFileName.split ( "\\." );
            String fileNameExtension = split[split.length - 1];

            String generatedName = System.currentTimeMillis () + "." + fileNameExtension;
            String path = "/uploads" + generatedName;

            Uploads uploads = Uploads
                    .builder ()
                    .contentType ( contentType )
                    .originalName ( originalFileName )
                    .size ( size )
                    .generatedName ( generatedName )
                    .path ( path )
                    .build ();

            Path uploadsPath = rootPath.resolve ( generatedName );

            dao.save ( uploads );

            Files.copy ( part.getInputStream (), uploadsPath, StandardCopyOption.REPLACE_EXISTING );
            return uploads;
        } catch (IOException e) {
            throw new RuntimeException ( "Something wrong try again" );
        }
    }

        public Uploads extractCover (Part part) {
        try {
            String contentType = "image/png";
            String[] split = part.getSubmittedFileName ().split ( "\\." );
            String originalFilename = split[0] + ".png";
            originalFilename = originalFilename.replaceAll ( ",", "_" );
            long size = part.getSize ();

            String generatedName = System.currentTimeMillis () + ".png";
            String path = "/uploads/" + generatedName;
            Uploads uploads = Uploads
                    .builder ()
                    .contentType ( contentType )
                    .originalName ( originalFilename )
                    .size ( size )
                    .generatedName ( generatedName )
                    .path ( path )
                    .build ();
            String uploadPath = rootPath.resolve ( generatedName ).toString ();
            dao.save ( uploads );

            PDDocument document = PDDocument.load ( part.getInputStream () );
            PDFRenderer pdfRenderer = new PDFRenderer ( document );
            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI ( 0, 300, ImageType.RGB );
            ImageIOUtil.writeImage ( bufferedImage, uploadPath, 300 );
            return uploads;
        } catch (IOException e) {
            throw new RuntimeException ( "Something wrong try again" );
        }
        }
    }

